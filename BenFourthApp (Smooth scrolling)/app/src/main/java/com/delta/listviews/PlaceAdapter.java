package com.delta.listviews;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by learnovate on 2/14/14.
 */
public class PlaceAdapter extends ArrayAdapter<Place> {
    Context mContext;
    int mLayoutResourceId;
    Place mData[];
    PlaceHolder holder;

    public PlaceAdapter(Context context, int resource, Place[] data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }
    @Override
    public Place getItem(int position) {
        return super.getItem(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId,parent,false);

            holder = new PlaceHolder();
            holder.nameView = row.findViewById(R.id.nameTextView);
            holder.zipView = row.findViewById(R.id.zipcodeTextView);
            holder.imageView = row.findViewById(R.id.imageView);

            row.setTag(holder);
        } else {
            holder = (PlaceHolder) row.getTag();

        }

        //get the data from the data array
        Place place = mData[position];

        //Setup and resue the same listener for each row
        holder.imageView.setOnClickListener(PopupListener);
        Integer rowPosition = position;
        holder.imageView.setTag(rowPosition);

        //setting the view to reflect the data we need to display
        holder.nameView.setText(place.mNameOfPlace);
        holder.zipView.setText(String.valueOf(place.mZipCode)); //always pay attention to your datatypes!

        //for getting the image
        int resId = mContext.getResources().getIdentifier(place.mNameOfImage,"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);

        //returning the row view (because this is called getView after all)
        return row;





        /*
        //get a reference to the different view elements we wish to update
        TextView nameView = row.findViewById(R.id.nameTextView);
        TextView zipView = row.findViewById(R.id.zipcodeTextView);
        ImageView imageView = row.findViewById(R.id.imageView);

        //get the data from the data array
        Place place = mData[position];

        //setting the view to reflect the data we need to display
        nameView.setText(place.mNameOfPlace);
        zipView.setText(String.valueOf(place.mZipCode)); //always pay attention to your datatypes!
        int resId = mContext.getResources().getIdentifier(place.mNameOfImage,"drawable",mContext.getPackageName());
        imageView.setImageResource(resId);

        //returning the row view (because this is called getView after all)
        return row;
        */
    }

    View.OnClickListener PopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Integer viewPosition = (Integer) view.getTag();
            Place p = mData[viewPosition];
            Toast.makeText(getContext(), p.mPopup, Toast.LENGTH_LONG).show();
        }
    };

    private static class PlaceHolder {
        TextView nameView;
        TextView zipView;
        ImageView imageView;

    }
}