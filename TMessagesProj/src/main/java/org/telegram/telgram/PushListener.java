package org.telegram.telgram;

import com.google.gson.Gson;

import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;

/**
 * Created by MindHunter on 2/22/2017.
 */
public class PushListener extends PusheListenerService {

    private PushListener _context;

    @Override
    public void onMessageReceived(final JSONObject message, JSONObject content) {

        try {
            _context = PushListener.this;
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
}
