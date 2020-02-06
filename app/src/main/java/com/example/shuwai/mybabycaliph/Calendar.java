package com.example.shuwai.mybabycaliph;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    package com.okedroid.myapplication;
/** * Created by FATHUR on 12/16/2015. */import java.text.DateFormat;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.GregorianCalendar;
    import java.util.Locale;
    import android.app.Activity;
    import android.os.Bundle;
    import android.os.Handler;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.AdapterView;
    import android.widget.AdapterView.OnItemClickListener;
    import android.widget.GridView;
    import android.widget.RelativeLayout;
    import android.widget.TextView;
    import android.widget.Toast;
    public class CalendarView extends Activity {
        public GregorianCalendar month, itemmonth;// calendar instances.
        public CalendarAdapter adapter;// adapter instance public Handler handler;// for grabbing some event values for showing the dot // marker. public ArrayList<String> items; // container to store calendar items which // needs showing the event marker
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calendar);
            Locale.setDefault( Locale.US );
            month = (GregorianCalendar) GregorianCalendar.getInstance();
            itemmonth = (GregorianCalendar) month.clone();
            items = new ArrayList<String>();
            adapter = new CalendarAdapter(this, month);
            GridView gridview = (GridView) findViewById(R.id.gridview);
            gridview.setAdapter(adapter);
            handler = new Handler();
            handler.post(calendarUpdater);
            TextView title = (TextView) findViewById(R.id.title);
            title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
            RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);
            previous.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    setPreviousMonth();
                    refreshCalendar();
                }
            });
            RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
            next.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    setNextMonth();
                    refreshCalendar();
                }
            });
            gridview.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    ((CalendarAdapter) parent.getAdapter()).setSelected(v);
                    String selectedGridDate = CalendarAdapter.dayString .get(position);
                    String[] separatedTime = selectedGridDate.split("-");
                    String gridvalueString = separatedTime[2].replaceFirst("^0*",
                            "");// taking last part of date. ie; 2 from 2012-12-02. int gridvalue = Integer.parseInt(gridvalueString);
// navigate to next or previous month on clicking offdays. if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
                showToast(selectedGridDate);
            }
        });
    }
    protected void setNextMonth() {
        if (month.get(GregorianCalendar.MONTH) == month .getActualMaximum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1);
        }
    }
    protected void setPreviousMonth() {
        if (month.get(GregorianCalendar.MONTH) == month .getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
    }
    protected void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
    public void refreshCalendar() {
        TextView title = (TextView) findViewById(R.id.title);
        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }
    public Runnable calendarUpdater = new Runnable() {
        @Override public void run() {
            items.clear();
// Print dates of the current week DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
            String itemvalue;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                items.add("2012-09-12");
                items.add("2012-10-07");
                items.add("2012-10-15");
                items.add("2012-10-20");
                items.add("2012-11-30");
                items.add("2012-11-28");
            }
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };
}
package com.okedroid.myapplication;
/** * Created by FATHUR on 12/16/2015. */
        import android.content.Context;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.GregorianCalendar;
        import java.util.List;
        import java.util.Locale;
public class CalendarAdapter extends BaseAdapter {
    private Context mContext;
    private java.util.Calendar month;
    public GregorianCalendar pmonth; // calendar instance for previous month /** * calendar instance for previous month for getting complete view */ public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int lastWeekDay;
    int leftDays;
    int mnthlength;
    String itemvalue, curentDateString;
    DateFormat df;
    private ArrayList<String> items;
    public static List<String> dayString;
    private View previousView;
    public CalendarAdapter(Context c, GregorianCalendar monthCalendar) {
        CalendarAdapter.dayString = new ArrayList<String>();
        Locale.setDefault( Locale.US );
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        mContext = c;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);
        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());
        refreshDays();
    }
    public void setItems(ArrayList<String> items) {
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items = items;
    }
    public int getCount() {
        return dayString.size();
    }
    public Object getItem(int position) {
        return dayString.get(position);
    }
    public long getItemId(int position) {
        return 0;
    }
    // create a new view for each item referenced by the Adapter public View getView(int position, View convertView, ViewGroup parent) {
    View v = convertView;
    TextView dayView;
    if (convertView == null) { // if it's not recycled, initialize some // attributes LayoutInflater vi = (LayoutInflater) mContext .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.calendar_item, null);
    }
    dayView = (TextView) v.findViewById(R.id.date);
// separates daystring into parts. String[] separatedTime = dayString.get(position).split("-");
// taking last part of date. ie; 2 from 2012-12-02 String gridvalue = separatedTime[2].replaceFirst("^0*", "");
// checking whether the day is in current month or not. if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
// setting offdays to white color. dayView.setTextColor(Color.WHITE);
    dayView.setClickable(false);
    dayView.setFocusable(false);
} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
        dayView.setTextColor(Color.WHITE);
        dayView.setClickable(false);
        dayView.setFocusable(false);
        } else {
// setting curent month's days in blue color. dayView.setTextColor(Color.BLUE);
        }
        if (dayString.get(position).equals(curentDateString)) {
        setSelected(v);
        previousView = v;
        } else {
        v.setBackgroundResource(R.drawable.list_item_background);
        }
        dayView.setText(gridvalue);
// create date string for comparison String date = dayString.get(position);
        if (date.length() == 1) {
        date = "0" + date;
        }
        String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
        if (monthStr.length() == 1) {
        monthStr = "0" + monthStr;
        }
// show icon if date is not empty and it exists in the items array ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
        if (date.length() > 0 && items != null && items.contains(date)) {
        iw.setVisibility(View.VISIBLE);
        } else {
        iw.setVisibility(View.INVISIBLE);
        }
        return v;
        }
public View setSelected(View view) {
        if (previousView != null) {
        previousView.setBackgroundResource(R.drawable.list_item_background);
        }
        previousView = view;
        view.setBackgroundResource(R.drawable.calendar_cel_selectl);
        return view;
        }
public void refreshDays() {
// clear items items.clear();
        dayString.clear();
        Locale.setDefault( Locale.US );
        pmonth = (GregorianCalendar) month.clone();
// month start day. ie; sun, mon, etc firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
// finding number of weeks in current month. maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
// allocating maximum row number for the gridview. mnthlength = maxWeeknumber * 7;
        maxP = getMaxP(); // previous month maximum day 31,30.... calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ... /** * Calendar instance for getting a complete gridview including the three * month's (previous,current,next) dates. */ pmonthmaxset = (GregorianCalendar) pmonth.clone();
/** * setting the start date as previous month's required date. */ pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);
/** * filling calendar gridview. */ for (int n = 0; n < mnthlength; n++) {
        itemvalue = df.format(pmonthmaxset.getTime());
        pmonthmaxset.add(GregorianCalendar.DATE, 1);
        dayString.add(itemvalue);
        }
        }
private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month .getActualMinimum(GregorianCalendar.MONTH)) {
        pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
        month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
        pmonth.set(GregorianCalendar.MONTH,
        month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        return maxP;
        }
        }
}
