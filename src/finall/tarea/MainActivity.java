package finall.tarea;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import finall.tarea.AppController;
import finall.tarea.CustomListAdapter;
import finall.tarea.MainActivity;
import finall.tarea.R;
import finall.tarea.post;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();


	private static final String url = "http://www.mocky.io/v2/5440667984d353f103f697c0";
	private ProgressDialog pDialog;
	private List<post> posts = new ArrayList<post>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, posts);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		
		pDialog.setMessage("Loading...");
		pDialog.show();

				JsonArrayRequest json = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();
						
						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {Log.d(TAG,"asdasd");

								JSONObject obj = response.getJSONObject(i);
								post carga = new post();
								carga.setTitle(obj.getString("title"));
								carga.setThumbnailUrl(obj.getString("image"));
								carga.setpuntos( obj.getString("points"));
								Log.d(TAG,"asdasd");

								
								posts.add(carga);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		
		AppController.getInstance().addToRequestQueue(json);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
