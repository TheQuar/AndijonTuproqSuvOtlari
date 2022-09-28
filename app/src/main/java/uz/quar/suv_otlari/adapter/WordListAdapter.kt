package uz.quar.suv_otlari.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.quar.suv_otlari.R
import uz.quar.suv_otlari.models.WordTable
import uz.quar.suv_otlari.utils.SystemUtils


class WordListAdapter() :
    ListAdapter<WordTable, WordListAdapter.WordViewHolder>(WORDS_COMPARATOR) {
    private var list_ = listOf<WordTable>()
    private lateinit var mainItemCLick: OnItemCLick


    fun setData(list: List<WordTable>?) {
        list_ = list!!
        submitList(list)
    }

    fun setOnItemCLick(onItemCLick: OnItemCLick) {
        mainItemCLick = onItemCLick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener { mainItemCLick.itemCLick(current?.id!!) }

    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)
        private val image = itemView.findViewById<ImageView>(R.id.imageView)
        fun bind(tableWord: WordTable) {
            wordItemView.text = tableWord.tur
            image.setImageBitmap(SystemUtils.openImage(itemView.context, "${tableWord.tur}.jpg"))
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<WordTable>() {
            override fun areItemsTheSame(oldItem: WordTable, newItem: WordTable): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: WordTable, newItem: WordTable): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<WordTable>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list_)
            } else {
                for (item in list_) {
                    if (item.tur?.lowercase()
                            ?.startsWith(constraint.toString().lowercase())!!
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as MutableList<WordTable>)

        }

    }


    interface OnItemCLick {
        fun itemCLick(id: Int)
    }
}
