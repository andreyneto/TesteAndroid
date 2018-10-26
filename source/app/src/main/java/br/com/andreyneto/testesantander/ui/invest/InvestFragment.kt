package br.com.andreyneto.testesantander.ui.invest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.convertDpToPixel
import br.com.andreyneto.testesantander.model.Screen
import kotlinx.android.synthetic.main.fragment_invest.*

class InvestFragment : Fragment(), InvestContract.View {

    private var mPresenter: InvestContract.Presenter? = null
    private var listRisk: List<View>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_invest, container, false)
        listRisk = listOf(invest_risk_1, invest_risk_2, invest_risk_3, invest_risk_4, invest_risk_5)
        return view
    }

    override fun setPresenter(presenter: InvestContract.Presenter) {
        mPresenter = presenter
    }

    override fun showData(model: Screen) {
        invest_title.text = model.title
        invest_investName.text = model.fundName
        invest_whatIs.text = model.whatIs
        invest_definition.text = model.definition
        invest_riskTitle.text = model.riskTitle
        invest_infoTitle.text = model.infoTitle

        setRisk(model.risk)

        invest_list_moreInfo.adapter = MoreInfoAdapter(model.moreInfo, context!!)
        invest_list_moreInfo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        invest_list_info.adapter = InfoAdapter(model.info + model.downInfo, context!!)
        invest_list_info.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
    }

    private fun setRisk(risk: Int) {
        val riskView = listRisk?.get(risk - 1)

        val params = riskView?.layoutParams
        params?.height = 15.convertDpToPixel(context!!)
        riskView?.layoutParams = params
//        val margins = riskView?.layoutParams as ViewGroup.MarginLayoutParams
//        margins.setMargins(0, 10.convertDpToPixel(context!!), 2, 0)
//        riskView.layoutParams = margins
    }

    companion object {

        fun newInstance(): InvestFragment {
            return InvestFragment()
        }
    }
}
