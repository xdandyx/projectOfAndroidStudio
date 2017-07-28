package cn.wdywae.wdywae;

import android.content.DialogInterface;

/**
 * Created by hasee on 2017/7/25.
 */
public  interface DalertCallback
{
    void isOk(DialogInterface dialog, int which);
    void isCancel(DialogInterface dialog, int which);
}
