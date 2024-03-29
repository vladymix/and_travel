package com.fabricio.travel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.fabricio.travel.adapters.BagAdapter
import com.fabricio.travel.adapters.BagSelecctedAdapter
import com.fabricio.travel.commons.SuitCase
import com.fabricio.travel.listeners.IOnItemsSeleccted
import com.fabricio.travel.services.BagResponse
import com.fabricio.travel.services.BagServiceAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import rx.android.schedulers.AndroidSchedulers


class MainActivity : AppCompatActivity(), Callback<BagResponse>, IOnItemsSeleccted<SuitCase> {

    private lateinit var adapter: BagAdapter
    private lateinit var adapterSeleccted: BagSelecctedAdapter

    override fun success(bagresponse: BagResponse?, response: Response?) {
        bagresponse?.run {
            adapter.addAll(bagresponse.suitcase)
        }

    }

    override fun failure(error: RetrofitError?) {
        error?.run {
            if (error.getKind() == RetrofitError.Kind.NETWORK) {
                Toast.makeText(applicationContext, R.string.network_error, Toast.LENGTH_LONG).show();
            }
        }

    }

    override fun itemSeleccted(items: List<SuitCase>) {

        adapterSeleccted.setData(items)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_list.setLayoutManager(GridLayoutManager(this, 1))
        rv_seleccted.setLayoutManager(GridLayoutManager(this, 1))

        this.adapter = BagAdapter(this, this)
        this.adapterSeleccted = BagSelecctedAdapter(this)

        this.rv_list.adapter = this.adapter
        this.rv_seleccted.adapter = this.adapterSeleccted

        this.requestBagList()
    }

    private fun requestBagList() {
        BagServiceAdapter.getBagList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    adapter.addAll(response.suitcase)
                })
    }
}
