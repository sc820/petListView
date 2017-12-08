package edu.nsysu.petlistview;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Frank on 2017/12/6.
 */

public class PetAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Pet> mDataSource;

    public PetAdapter(Context context, ArrayList<Pet> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.list_item_pet, parent, false);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.pet_list_thumbnail);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.pet_list_title);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.pet_list_subtitle);
            holder.detailTextView = (TextView) convertView.findViewById(R.id.pet_list_detail);

            // hang onto this holder for future
            convertView.setTag(holder);
        }
        else {
            // skip all the expensive inflation/findViewById and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        // Get relevant subviews of row view
        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        TextView detailTextView = holder.detailTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        //Get corresponding pet for row
        Pet pet = (Pet) getItem(position);

        // Update row view's textviews to display pet information
        titleTextView.setText(pet.title);
        subtitleTextView.setText(pet.description);
        detailTextView.setText(pet.label);

        // Use Picasso to load the image. Temporarily have a placeholder in case it's slow to load
        Picasso.with(mContext).load(pet.imageUrl).placeholder(R.mipmap
                .ic_launcher).fit().centerCrop().into(thumbnailImageView);


        return convertView;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }

}
