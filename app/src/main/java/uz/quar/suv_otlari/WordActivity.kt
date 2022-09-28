package uz.quar.suv_otlari

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_word.*
import uz.quar.suv_otlari.databinding.ActivityWordBinding
import uz.quar.suv_otlari.utils.SystemUtils
import uz.quar.suv_otlari.vm.WordViewModel

class WordActivity : AppCompatActivity() {
    var id = 0
    lateinit var binding: ActivityWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val wordViewModel = ViewModelProvider(this)[WordViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            id = extras.getInt("id")
        }

        val word = wordViewModel.getWord(id)

        with(word) {
            supportActionBar?.title = word.tur
            iv_photo.setImageBitmap(SystemUtils.openImage(applicationContext, "${word.tur}.jpg"))

            tv_kingdom.text = kingdom
            tv_phylum.text = phylum
            tv_class.text = sinf
            tv_order.text = order
            tv_family.text = family
            tv_genus.text = genus
            tv_dateidentified.text = dateidentified
            tv_yearidentified.text = yearidentified
            tv_collectornember.text = collectornember
            tv_continent.text = continent
            tv_country.text = country
            tv_soil.text = soil
            tv_region.text = region
            tv_seasonaloccurrence.text = seasonaloccurrence
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}