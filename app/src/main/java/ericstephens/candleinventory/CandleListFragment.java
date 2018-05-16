package ericstephens.candleinventory;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;

import ericstephens.candleinventory.dummy.DummyContent;
import ericstephens.candleinventory.dummy.DummyContent.DummyItem;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CandleListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<String> candleNames = new ArrayList<>();
    private List<String> inventoryAmount = new ArrayList<>();
    private DatabaseHelper db = new DatabaseHelper(getActivity());
    private Cursor cursor;
    RecyclerView recyclerView;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CandleListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CandleListFragment newInstance(int columnCount) {
        columnCount = 2;
        CandleListFragment fragment = new CandleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        Log.d("DEBUG", "IT GOES HERE");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DEBUG", "ITS INHERE");
        View view = inflater.inflate(R.layout.fragment_candlelist_list, container, false);
        getAllData();
        recyclerView = view.findViewById(R.id.candleList);
        MyCandleListRecyclerViewAdapter adapter = new MyCandleListRecyclerViewAdapter(getContext(), candleNames, inventoryAmount);
        LinearLayoutManager mLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        db = new DatabaseHelper(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getAllData()
    {
        cursor = db.getAllData();
        if (cursor.getCount() == 0)
            return;

        Log.d("DEBUG", "IT GOT PAST THE RETURN");
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            candleNames.add(cursor.getString(1));
            inventoryAmount.add(cursor.getString(2));

        }
        mColumnCount = candleNames.size();
        Log.d("CANDLE NAME", candleNames.get(0));
    }

    public void getSearchData(String candleName)
    {

        cursor = db.getSearch(candleName);

        if (cursor.getCount() == 0)
            return;

        else
        {
            candleNames.clear();
            inventoryAmount.clear();
            StringBuffer buffer = new StringBuffer();
            while(cursor.moveToNext())
            {
                candleNames.add(cursor.getString(1));
                inventoryAmount.add(cursor.getString(2));
            }
            mColumnCount = 1;
            recyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(EditText invetoryAmount);
    }
}
