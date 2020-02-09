package codegene.femicodes.aghedojosephfemi.ui.filterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codegene.femicodes.aghedojosephfemi.R
import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity

class FilterListAdapter(private val clickListener: (FilterEntity) -> Unit) :
    ListAdapter<FilterEntity, FilterListAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dateRangeTv: TextView = itemView.findViewById(R.id.dateRangeTv)
        private val genderTv: TextView = itemView.findViewById(R.id.genderTv)
        private val countryTv: TextView = itemView.findViewById(R.id.countriesTv)
        private val colorTv: TextView = itemView.findViewById(R.id.colorTv)

        fun bind(
            item: FilterEntity,
            clickListener: (FilterEntity) -> Unit
        ) = with(itemView) {

            if (item.startYear == null || item.endYear == null) {
                dateRangeTv.visibility = View.GONE
            } else {
                dateRangeTv.text =
                    String.format("Date Range: %s - %s", item.startYear, item.endYear)
            }

            if (item.gender.isNullOrBlank()) {
                genderTv.visibility = View.GONE
            } else {
                genderTv.text = String.format("Gender: %s", item.gender)
            }
            if (item.countries.isNullOrEmpty()) {
                countryTv.visibility = View.GONE
            } else {
                countryTv.text = String.format(
                    "Countries: %s",
                    item.countries.toString().replace("[", "").replace("]", "")
                )
            }
            if (item.colors.isNullOrEmpty()) {
                colorTv.visibility = View.GONE
            } else {
                colorTv.text = String.format(
                    "Color: %s",
                    item.colors.toString().replace("[", "").replace("]", "")
                )
            }

            setOnClickListener {
                clickListener(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<FilterEntity>() {
    override fun areItemsTheSame(oldItem: FilterEntity, newItem: FilterEntity): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: FilterEntity, newItem: FilterEntity): Boolean {
        return oldItem == newItem
    }
}