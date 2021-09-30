package permission.manager

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.R
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.reflect.KClass


class iTools  {

    var currentActivity:Any
    constructor(activity:Activity){
        currentActivity = activity
    }
    constructor(context:Context){
        currentActivity = context
    }

    fun checkPermission(permissionName:String):Boolean{
        try {
            return ContextCompat.checkSelfPermission( currentActivity as Context, permissionName ) == PackageManager.PERMISSION_GRANTED
        }
        catch (ex:Exception){
            toastShort(ex.message.toString())
        }
        return false
    }

    fun iNeedPermission(permissionName: String , requestCode:Int = 26697){
        try {
            ActivityCompat.requestPermissions(currentActivity as Activity, arrayOf(permissionName), requestCode)
        }
        catch (ex:Exception){
            toastShort(ex.message.toString())
        }
    }

    fun toastShort(text:String){
        Toast.makeText(currentActivity as Context,text , Toast.LENGTH_SHORT ).show()
    }

    fun toastLong(text:String){
        Toast.makeText(currentActivity as Context ,text , Toast.LENGTH_LONG ).show()
    }

    fun fillSpinner(spinner:Spinner , items:Array<String>){
        try {
            val a = ArrayAdapter(currentActivity as Context, android.R.layout.simple_spinner_item, items)
            spinner.adapter = a
        }
        catch (ex:Exception){
            toastShort(ex.message.toString())
        }
    }

    fun openActivity(activity:KClass<*>){
        try {
            var i = Intent(currentActivity as Context , activity.java)
            ContextCompat.startActivity(currentActivity as Context , i , null)
        }
        catch (ex:Exception){
            toastShort(ex.message.toString())
        }

    }

    fun openActivityForResult(activity:KClass<*> , requestCode: Int) {
        try {
            var i = Intent(currentActivity as Context, activity.java)
            ActivityCompat.startActivityForResult(currentActivity as Activity, i, requestCode, null)
        }
        catch (ex:Exception){
            toastShort(ex.message.toString())
        }
    }

    fun runShortSound(uri:Int){
        try {
            MediaPlayer.create(currentActivity as Context, uri).start()
        }
        catch (ex:Exception){
          toastShort(ex.message.toString())
        }

    }

    fun showCustomDialog(title:String , message:String , activity: Activity) {
        try {
            var mAlertDialog = AlertDialog.Builder(currentActivity as Context)
            mAlertDialog.setTitle(title)  ///alert dialog title
            mAlertDialog.setMessage(message!!) //alert dialog message
             //mAlertDialog.setIcon(com.ahmedsaad.tictactoy.R.mipmap.ic_launcher)   ///alert dialog icon
            //add negative /yes button to alert dialog
            mAlertDialog.setNegativeButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialong: DialogInterface?, which: Int) {
                    ///perform your action here when click Yes
                    ActivityCompat.recreate(activity)
                }
            })
            //add positive  button to alert dialog
            mAlertDialog.setPositiveButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialong: DialogInterface?, which: Int) {
                    dialong!!.dismiss() ///dismiss dialog  when user click No
                    ActivityCompat.finishAffinity(activity)
                }
            })
            mAlertDialog.show()  ///show alert dialog
        }
        catch (ex:Exception){toastShort(ex.message.toString())}
    }

}