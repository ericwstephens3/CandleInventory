package ericstephens.candleinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import ericstephens.candleinventory.CandleListFragment.OnListFragmentInteractionListener;
import ericstephens.candleinventory.dummy.DummyContent.DummyItem;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCandleListRecyclerViewAdapter extends RecyclerView.Adapter<MyCandleListRecyclerViewAdapter.ViewHolder> {

    private final List<String> candleNames;
    private final List<String> inventoryAmounts;
    private Context context;

    public MyCandleListRecyclerViewAdapter(Context context, List<String> candleNames, List<String> inventoryAmts) {
        this.candleNames = candleNames;
        this.inventoryAmounts = inventoryAmts;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_candlelist, parent, false);
        Log.d("DEBU", "ITS IN HERE");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
       Log.d("THIS IS POSITION", Integer.toString(position));
        holder.candleName.setText(candleNames.get(position));
        holder.inventoryAmt.setText(inventoryAmounts.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.inventoryAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!holder.inventoryAmt.isFocused())
                {
                    //send to file
                    inventoryAmounts.add(position, editable.toString());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("ITEM COUNT", "ITS IN HERE");
        return candleNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView candleName;
        public final EditText inventoryAmt;
        public ViewHolder(View view) {
            super(view);
            Log.d("DEBU", "ITS IN HERE");
            mView = view;
            candleName = view.findViewById(R.id.candleName);
            inventoryAmt = view.findViewById(R.id.inventoryAmount);
        }

        //@Override
        //public String toString() {
            //return super.toString() + " '" + mContentView.getText() + "'";
        //}
    }
}
