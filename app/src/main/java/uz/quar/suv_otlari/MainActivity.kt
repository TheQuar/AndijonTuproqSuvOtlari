package uz.quar.suv_otlari


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import uz.quar.suv_otlari.adapter.WordListAdapter
import uz.quar.suv_otlari.databinding.ActivityMainBinding
import uz.quar.suv_otlari.vm.WordViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmdoel: WordViewModel
    private val adapters = WordListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmdoel = ViewModelProvider(this)[WordViewModel::class.java]

        setupRecycler()
        viewmdoel.getAllWords()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapters.getFilter().filter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun setupRecycler() {
        viewmdoel.allWords.observe(this) {
            adapters.setData(it)

        }

        adapters.setOnItemCLick(object : WordListAdapter.OnItemCLick {
            override fun itemCLick(id: Int) {
                val i = Intent(this@MainActivity, WordActivity::class.java)
                i.putExtra("id", id)
                startActivity(i)
            }
        })

        binding.recyclerView1.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = adapters

        }
    }

}