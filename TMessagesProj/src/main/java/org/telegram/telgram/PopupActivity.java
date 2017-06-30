package org.telegram.telgram;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.telegram.messenger.R;
import org.telegram.telgram.PModel;


/**
 * Created by MindHunter on 2/28/2017.
 */
public class PopupActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context _context = PopupActivity.this;
        final PModel model = (PModel) getIntent().getSerializableExtra("model");

        try {
            View dlgView = View.inflate(_context, R.layout.push_dialog, null);

            ((TextView) dlgView.findViewById(R.id.txtTitle)).setText(model.getTitle());
            ((TextView) dlgView.findViewById(R.id.txtContent)).setText(model.getContent());
            Button btnShow = (Button) dlgView.findViewById(R.id.btnShow);
            btnShow.setText(model.getButtonCaption());

            ImageView img = (ImageView) dlgView.findViewById(R.id.imgPicture);
            //Glide.with(_context).load(model.getImageUrl()).fitCenter().into(img);

            final AlertDialog dlg = new AlertDialog.Builder(_context).create();
            dlg.setCancelable(!model.isForce());
            dlg.setView(dlgView);
            dlg.show();

            dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    finish();
                }
            });
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                    startActivity(browserIntent);
                    dlg.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
