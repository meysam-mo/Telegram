package org.telegram.telgram;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.query.DraftQuery;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.JoinGroupAlert;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;

import java.util.ArrayList;

/**
 * Created by MindHunter on 6/30/2017.
 */

public class Funcs
{
    public static void pushNotify(Context _context, PModel model) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
            PendingIntent pendingIntent = PendingIntent.getActivity(_context, 0, browserIntent, 0);

            NotificationCompat.Builder mBuilder =
                    new android.support.v4.app.NotificationCompat.Builder(_context)
                            .setAutoCancel(true)
                            .setOngoing(model.isForce())
                            .setSmallIcon(android.R.drawable.ic_dialog_alert)
                            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(model.getTitle())
                            .setSubText(model.getContent());

            NotificationManager mNotificationManager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(2020, mBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void autoOpen(Context _context, PModel model) {
        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(model.getLink()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void showPopup(Context _context, final PModel model) {

        try
        {
            Intent intent = new Intent(_context, PopupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("model",model);
            _context.startActivity(intent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void joinChannel(String group)
    {
        if (group != null)
        {
            TLRPC.TL_messages_importChatInvite req = new TLRPC.TL_messages_importChatInvite();
            req.hash = group;
            ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate()
            {
                @Override
                public void run(final TLObject response, final TLRPC.TL_error error)
                {
                    if (error == null) {
                        TLRPC.Updates updates = (TLRPC.Updates) response;
                        MessagesController.getInstance().processUpdates(updates, false);
                    }
                }
            }, ConnectionsManager.RequestFlagFailOnServerErrors);
        }
    }
}
