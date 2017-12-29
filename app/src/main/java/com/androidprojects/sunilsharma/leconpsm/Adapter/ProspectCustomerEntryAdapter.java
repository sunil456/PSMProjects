package com.androidprojects.sunilsharma.leconpsm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.androidprojects.sunilsharma.leconpsm.Activities.DetailsActicity;
import com.androidprojects.sunilsharma.leconpsm.Helper.ItemLongClickListener;
import com.androidprojects.sunilsharma.leconpsm.Model.ProspectUser;
import com.androidprojects.sunilsharma.leconpsm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil sharma on 12/25/2017.
 */

public class ProspectCustomerEntryAdapter extends
        RecyclerView.Adapter<ProspectCustomerEntryAdapter.ProspectViewHolder> implements Filterable
{
    private Context context;
    private List<ProspectUser> prospectUserList;
    private List<ProspectUser> mFilteredList;

    String ProspectCompanyName;
    String ProspectType;
    int ProspectArea;
    String ProspectAddress;


    public ProspectCustomerEntryAdapter(Context context, List<ProspectUser> prospectUserList)
    {
        this.context = context;
        this.prospectUserList = prospectUserList;
        mFilteredList = prospectUserList;
    }

    @Override
    public ProspectViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.prospect_customer_entry_layout , parent , false);

        return new ProspectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProspectViewHolder holder, int position)
    {
        // ProspectUser prospectUser = prospectUserList.get(position);

        final String CompanyName = prospectUserList.get(position).getCOMPANYNAME();
        final String Type = prospectUserList.get(position).getType();
        final int Area = prospectUserList.get(position).getAREA();
        final String Address = prospectUserList.get(position).getAdd1();

        // holder.txtCompanyName.setText(prospectUser.getCOMPANYNAME());
        holder.txtCompanyName.setText(mFilteredList.get(position).getCOMPANYNAME());
        holder.txtType.setText(mFilteredList.get(position).getType());
        holder.txtArea.setText(String.valueOf(mFilteredList.get(position).getAREA()));
        holder.txtAddress.setText(mFilteredList.get(position).getAdd1());
        holder.txtPersonName.setText(mFilteredList.get(position).getCNAME());
        holder.txtMobileNumber.setText(mFilteredList.get(position).getMobile());
        holder.txtLandmark.setText(mFilteredList.get(position).getLandMark());
        holder.txtEmailAddress.setText(mFilteredList.get(position).getEmail());


        /** LONG CLICK*/
        holder.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onLongClick(int pos)
            {
                ProspectCompanyName = CompanyName;
                ProspectType = Type;
                ProspectArea = Area;
                ProspectAddress = Address;
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return mFilteredList.size();
    }

    @Override

    public Filter getFilter()
    {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charString = charSequence.toString();

                if(charString.isEmpty())
                {
                    mFilteredList = prospectUserList;
                }
                else
                {
                    List<ProspectUser> filteredList = new ArrayList<>();

                    for (ProspectUser prospectUser : prospectUserList)
                    {
                        if (prospectUser.get$id().toLowerCase().contains(charString) ||
                                prospectUser.getAdd1().toLowerCase().contains(charString) ||
                                prospectUser.getCNAME().toLowerCase().contains(charString) ||
                                prospectUser.getCOMPANYNAME().toLowerCase().contains(charString) ||
                                prospectUser.getType().toLowerCase().contains(charString) ||
                                prospectUser.getEmail().toLowerCase().contains(charString) ||
                                prospectUser.getLandMark().toLowerCase().contains(charString) ||
                                prospectUser.getMobile().toLowerCase().contains(charString) ||
                                Integer.toString(prospectUser.getAREA()).toLowerCase().contains(charString))
                        {
                            filteredList.add(prospectUser);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                mFilteredList = (List<ProspectUser>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void getSelectedContextMenuItem(MenuItem item)
    {
        this.openDetailActivity(item.getTitle().toString());
    }

    /** Start New Activity*/
    private void openDetailActivity(String choice)
    {
        Intent intent = new Intent(context , DetailsActicity.class);

        intent.putExtra("COMPANYNAME" , ProspectCompanyName);
        intent.putExtra("Type" , ProspectType);
        intent.putExtra("AREA" , ProspectArea);
        intent.putExtra("Add1" , ProspectAddress);
        intent.putExtra("CHOICE" , choice);

        context.startActivity(intent);
    }

    public class ProspectViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener , View.OnCreateContextMenuListener
    {
        public TextView txtCompanyName;
        public TextView txtType;
        public TextView txtArea;
        public TextView txtAddress;
        public TextView txtPersonName;
        public TextView txtMobileNumber;
        public TextView txtLandmark;
        public TextView txtEmailAddress;
        ItemLongClickListener itemLongClickListener;

        public ProspectViewHolder(View itemView)
        {
            super(itemView);
            // context = ctx;
            txtCompanyName = (TextView) itemView.findViewById(R.id.txtCompanyName);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            txtArea = (TextView) itemView.findViewById(R.id.txtArea);
            txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtMobileNumber = (TextView) itemView.findViewById(R.id.txtMobileNumber);
            txtLandmark = (TextView) itemView.findViewById(R.id.txtLandmark);
            txtEmailAddress = (TextView) itemView.findViewById(R.id.txtEmailAddress);

            itemView.setOnLongClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }


        public void setItemLongClickListener(ItemLongClickListener ic)
        {
            this.itemLongClickListener = ic;
        }

        /**
         * Called when a view has been clicked and held.
         *
         * @param v The view that was clicked and held.
         * @return true if the callback consumed the long click, false otherwise.
         */
        @Override
        public boolean onLongClick(View v)
        {
            this.itemLongClickListener.onLongClick(getLayoutPosition());
            return false;
        }

        /**
         * Called when the context menu for this view is being built. It is not
         * safe to hold onto the menu after this method returns.
         *
         * @param menu     The context menu that is being built
         * @param v        The view for which the context menu is being built
         * @param menuInfo Extra information about the item for which the
         *                 context menu should be shown. This information will vary
         */
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            /** OUR CONTEXT MENU*/
            menu.setHeaderTitle("Go To: ");
            menu.add(0,0,0, "Details");
            menu.add(0,1,0, "Update");
        }
    }
}

