package codegene.femicodes.aghedojosephfemi.ui.filteredResult


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codegene.femicodes.aghedojosephfemi.R
import codegene.femicodes.aghedojosephfemi.local.entity.CarOwnerEntity

class FilteredResultAdapter :
    ListAdapter<CarOwnerEntity, FilteredResultAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cars, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilteredResultAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fullNameTv: TextView = itemView.findViewById(R.id.fullNameTv)
        private val emailTv: TextView = itemView.findViewById(R.id.emailTv)
        private val countryTv: TextView = itemView.findViewById(R.id.countryTv)
        private val carMakeTv: TextView = itemView.findViewById(R.id.carMakeTv)
        private val carColorTv: TextView = itemView.findViewById(R.id.carColorTv)
        private val carYearTv: TextView = itemView.findViewById(R.id.carYearTv)
        private val genderTv: TextView = itemView.findViewById(R.id.genderTv)
        private val jobTitleTv: TextView = itemView.findViewById(R.id.jobTitleTv)
        private val bioTv: TextView = itemView.findViewById(R.id.bioTv)

        fun bind(item: CarOwnerEntity) = with(itemView) {
            fullNameTv.text = String.format("Full Name: %s %s", item.firstName, item.lastName)
            emailTv.text = String.format("Email: %s", item.email)
            countryTv.text = String.format("Country: %s", item.country)
            carMakeTv.text = String.format("Car Make: %s", item.carModel)
            carColorTv.text = String.format("Color: %s ", item.carColor)
            carYearTv.text = String.format("Year: %s", item.carModelYear)
            genderTv.text = String.format("Gemder: %s", item.gender)
            jobTitleTv.text = String.format("Job Title: %s", item.jobTitle)
            bioTv.text = String.format("Bio: %s", item.bio)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<CarOwnerEntity>() {
    override fun areItemsTheSame(oldItem: CarOwnerEntity, newItem: CarOwnerEntity): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: CarOwnerEntity, newItem: CarOwnerEntity): Boolean {
        return oldItem == newItem
    }
}