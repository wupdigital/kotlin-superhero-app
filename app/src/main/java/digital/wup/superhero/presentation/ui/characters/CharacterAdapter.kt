package digital.wup.superhero.presentation.ui.characters


import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.QuickContactBadge
import android.widget.TextView

import digital.wup.superhero.R
import digital.wup.superhero.data.model.Character
import digital.wup.superhero.presentation.Navigation
import digital.wup.superhero.presentation.ui.characters.CharacterAdapter.ViewHolder

class CharacterAdapter(private val charactersDataSet: Array<Character>, private val charactersView: CharactersContract.CharactersView) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false) as TextView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = charactersDataSet[holder.adapterPosition].name
        holder.textView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Navigation.CHARACTER_ID, charactersDataSet[holder.adapterPosition].id.toString())

            charactersView.navigateToDetails(bundle)
        }
    }

    override fun getItemCount(): Int {
        return charactersDataSet.size
    }

    class ViewHolder(var textView: TextView) : RecyclerView.ViewHolder(textView)
}
