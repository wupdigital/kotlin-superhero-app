package digital.wup.superhero.presentation.ui.characters

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import javax.inject.Inject

import dagger.android.AndroidInjection
import digital.wup.superhero.R
import digital.wup.superhero.data.model.Character
import digital.wup.superhero.presentation.Navigation
import digital.wup.superhero.presentation.ui.details.DetailsActivity

class CharactersActivity : AppCompatActivity(), CharactersContract.CharactersView {

    @Inject
    internal lateinit var presenter: CharactersContract.CharactersPresenter

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        presenter.takeView(this)
        presenter.loadCharacters()
    }

    override fun showLoadingIndicator() {

    }

    override fun hideLoadingIndicator() {

    }

    override fun showMoreLoadingIndicator() {

    }

    override fun hideMoreLoadingIndicator() {

    }

    override fun showCharacters(characters: Array<Character>) {
        recyclerView.adapter = CharacterAdapter(characters, this)
    }

    override fun showLoadingCharactersError(message: String) {

    }

    override fun showNoCharacters() {

    }

    override fun navigateToDetails(bundle: Bundle) {
        val navigate = Intent(this, DetailsActivity::class.java)
        navigate.putExtra(Navigation.EXTRA, bundle)
        startActivity(navigate)
    }
}
