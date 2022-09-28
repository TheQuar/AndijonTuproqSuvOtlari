package uz.quar.suv_otlari.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.quar.suv_otlari.models.WordTable

class WordViewModel(application: Application) :
    AndroidViewModel(application) {

    private val wordRepository = WordRepository(application)
    var allWords = MutableLiveData<List<WordTable>>()


    fun getAllWords() {
        viewModelScope.launch {
            allWords.postValue(wordRepository.getWordAll())
        }

    }


    fun getWord(id: Int): WordTable {
        return wordRepository.getWord(id)
    }
}
