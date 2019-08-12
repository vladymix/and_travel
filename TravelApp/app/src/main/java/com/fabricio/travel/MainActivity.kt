package com.fabricio.travel

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fabricio.travel.adapters.BagAdapter
import com.fabricio.travel.services.BagResponse
import com.fabricio.travel.services.BagServiceAdapter
import kotlinx.android.synthetic.main.activity_main.*

import retrofit.Callback;
import retrofit.RetrofitError
import retrofit.client.Response
import rx.android.schedulers.AndroidSchedulers
import androidx.recyclerview.widget.GridLayoutManager


class MainActivity : AppCompatActivity(), Callback<BagResponse> {


    private lateinit var adapter: BagAdapter

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        litView.setLayoutManager(GridLayoutManager(this, 1))

        this.adapter = BagAdapter(this)
        this.litView.adapter = this.adapter
        requestBagList()


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
