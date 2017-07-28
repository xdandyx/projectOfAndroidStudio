package cn.wdywae.wdywae;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by hasee on 2017/7/25.
 */
public class Dalert
{
    public Dalert(Context context,String title,String message,
                  boolean canCancelable, final DalertCallback dalertCallback)
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(canCancelable);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dalertCallback.isOk(dialog,which);
            }
        });
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dalertCallback.isCancel(dialog,which);
            }
        });
        alertDialog.show();
    }
}
