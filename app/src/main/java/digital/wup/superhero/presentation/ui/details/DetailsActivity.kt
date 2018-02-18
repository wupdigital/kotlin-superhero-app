package digital.wup.superhero.presentation.ui.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import digital.wup.superhero.R
import digital.wup.superhero.data.model.Character
import digital.wup.superhero.presentation.Navigation
import javax.inject.Inject

open class DetailsActivity : AppCompatActivity(), DetailsContract.DetailsView {

    @Inject
    internal lateinit var presenter: DetailsContract.DetailsPresenter
    @Inject
    internal lateinit var picasso: Picasso

    private var nameTextView: TextView? = null
    private var thumbnailImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        nameTextView = findViewById(R.id.name)
        thumbnailImageView = findViewById(R.id.thumbnail)

        val intent = intent
        val bundle = intent.getBundleExtra(Navigation.EXTRA)

        presenter.takeView(this)
        presenter.loadCharacter(bundle.getString(Navigation.CHARACTER_ID))
    }

    override fun showLoadingIndicator() {

    }

    override fun hideLoadingIndicator() {

    }

    override fun showCharacter(characters: Character) {
        nameTextView!!.text = characters.name

        picasso.load(characters.thumbnail.path + "." + characters.thumbnail.extension).into(thumbnailImageView)
    }

    override fun showLoadingCharacterError(message: String) {

    }

    override fun showNoCharacter() {

    }
}
