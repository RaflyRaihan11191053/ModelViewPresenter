package com.example.modelviewpresenter

import android.text.TextUtils

class MainPresenterImp(private val view: MainView): MainPresenter {

    private val HASIL = mutableListOf<Hasil>()

    override fun tambahHasil(numberOne: String, numberTwo: String) {
        if (TextUtils.isEmpty(numberOne) || TextUtils.isEmpty(numberTwo)) {
            view.showMessage("Input tidak boleh kosong")
        } else {
            val jumlah = numberOne.toInt() + numberTwo.toInt()
            HASIL.run {
                add(Hasil(jumlah))
            }
            view.showMessage("Hasil Penjumlahan: ${jumlah}")
            view.clearField()
        }
    }

    override fun clearHasil() {
        if (HASIL.size != 0) {
            HASIL.clear()
            view.showMessage("Data Berhasil Dihapus")
        } else {
            view.showMessage("Data Masih Kosong")
        }
    }

    override fun loadHasil() {
        if(HASIL.size == 0){
            view.showMessage("Data Kosong")
        } else {
            var allData = ""

            for(i in 0 until HASIL.size){
                allData += "Hasil : " + HASIL[i].hasilHitung + "\n\n"
            }

            allData += "Total : " + HASIL.size

            view.showData(allData)
        }
    }
}