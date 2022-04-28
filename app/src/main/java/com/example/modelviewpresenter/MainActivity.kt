package com.example.modelviewpresenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.modelviewpresenter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenterImp: MainPresenterImp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenterImp = MainPresenterImp(this)

        binding.btnSubmit.setOnClickListener {
            presenterImp.tambahHasil(
                binding.etNumberOne.text.toString(),
                binding.etNumberTwo.text.toString()
            )
        }

        binding.btnRiwayat.setOnClickListener {
            presenterImp.loadHasil()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showData(data: String) {
        AlertDialog
            .Builder(this)
            .setNegativeButton("Close"){dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("Hapus Data"){dialog, _ ->
                //confirm
                AlertDialog.Builder(this)
                    .setPositiveButton("Ya"){dialog, _ ->
                        presenterImp.clearHasil()
                        dialog.dismiss()
                    }
                    .setNegativeButton("Batal"){dialog, _ ->
                        dialog.dismiss()
                    }
                    .setTitle("Konfirmasi")
                    .setMessage("Hapus Riwayat?")
                    .create()
                    .show()

                dialog.dismiss()
            }
            .setTitle("History")
            .setMessage(data)
            .create()
            .show()
    }

    override fun clearField() {
        binding.etNumberOne.text!!.clear()
        binding.etNumberTwo.text!!.clear()
    }
}