package codegene.femicodes.aghedojosephfemi.ui.addFilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import codegene.femicodes.aghedojosephfemi.R
import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity
import codegene.femicodes.aghedojosephfemi.ui.MainActivity
import codegene.femicodes.aghedojosephfemi.ui.utils.isValid
import codegene.femicodes.aghedojosephfemi.ui.utils.isValidInteger
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_filter.*
import javax.inject.Inject


class AddFilterFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val addFilterViewModel: AddFilterViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_filter_btn.setOnClickListener {

            if (gender_et.isValid(gender_layout) && countries_et.isValid(countries_layout)
                && colors_et.isValid(colors_layout) && start_date_et.isValidInteger(
                    start_date_layout
                )
                && end_date_et.isValidInteger(end_date_layout)
            ) {

                val gender = gender_et.text.toString().trim()
                val countryString = countries_et.text.toString().trim()
                val colorsString = colors_et.text.toString().trim()
                val startDate = start_date_et.text.toString().trim()
                val endDate = end_date_et.text.toString().trim()


                val countries = countryString.split(",")
                val colors = colorsString.split(",")

                val filter = FilterEntity(
                    gender = gender,
                    startYear = startDate.toInt(),
                    id = -1,
                    countries = countries,
                    endYear = endDate.toInt(),
                    colors = colors
                )

                addFilterViewModel.addFilter(filter)
                (activity as? MainActivity)?.onBackPressed()

            }

        }

    }


}