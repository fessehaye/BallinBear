package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import model.Album;
import model.Photo;
// Controller is a static class and can be accessed from both model and view
public class Controller 
{

    static ArrayList<Album> albums = new ArrayList<Album>();
    private static final String fileName = "albumsfile.data";
    
//    public Controller()
//    {
//
//        albums = null;
//    }

    
    // get album i
    public static Album getAlbum(int i)
    {
        Log.e("getting Album", "album " + albums.get(i).getAlbumName());
        return albums.get(i);

    }

 // add album i with name s with its first photo p
    public static void addAlbum(String albName, Uri imageUri, String comment){
        if(albums.size() > 0)
        Log.e("adding Album", "adding album " + albName + ", other album has name " + albums.get(0).getAlbumName());
        Album temp = new Album(albName);
        temp.addPhoto(new Photo(comment,imageUri));
        albums.add(0,temp); // TODO Where we overwrite the album names
        if(albums.size() > 1)
          Log.e("adding Album", "adding album " + albName + ", other album has name " + albums.get(1).getAlbumName());

    } 

 // delete album i
    public static void deleteAlbum(int i)
    {
        Log.e("Deleting Album", "album " + i);
        albums.remove(i);
    } 

 // change the name of album i to s
    public static void updateAlbum(int i, String s)
    {   
        Log.e("updating Album", "album " + i + " changing name from " + albums.get(i).getAlbumName() + " to " + s);
        Album temp = albums.get(i);
        temp.setAlbumName(s);
        albums.set(i, temp);
    } 

 // get photo j from album i
    public static Photo getPhoto(int i, int j){
        Log.e("getting photo", "get " + j + " photo from album " + i);
      return  albums.get(i).getPhoto(j);
    } 

 // add photo p to i
    public static void addPhoto(int i, Uri imageUri, String comment)
    {
        Log.e("addPhoto", "add photo with comment " + comment + " to album " + i);
        Album temp = albums.get(i);
        temp.addPhoto(new Photo(comment, imageUri));
        albums.set(i, temp);
    } 

 // delete photo j
    public static void deletePhoto(int i, int j)
    {
        Log.e("deletePhoto", "delete photo " + j + " from album " + i);
        Album temp = albums.get(i);
        temp.deletePhoto(j);
        albums.set(i, temp);
    } 

 // update the comments on this photo
    public static void updatePhoto(int i, int j, String s)
    {
        Log.e("updatePhoto", "update photo " + j + " in album " + i + " with comment " + s);
        Album tempAlbum = albums.get(i);
        Photo tempPhoto = tempAlbum.getPhoto(j);
        tempPhoto.setComment(s);
        tempAlbum.updatePhoto(j, tempPhoto);
        albums.set(i, tempAlbum);
    } 

    
    public static String[] getAlbumNames(){
        
        
        
        Log.e("getting album names", "albums.size " + albums.size());
        
        if(albums.size() == 0) // Cant cast albums to string[] using .toArray because albums isnt an arraylist of strings
            return new String[] {};
        
        String[] albNames = new String[albums.size()];
        
        for (int i = 0; i < albums.size(); i++){
            Log.e("", "album " + i + " has name " + albums.get(i).getAlbumName());
            albNames[i] = albums.get(i).getAlbumName();
        }
        return albNames;
    }
    
 // Save the ArrayList<HashMap<String,String>> to a file
    public static void saveObject(Context c)
    {

        FileOutputStream stream = null;
        try
        {
            
            stream = c.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(stream);
            out.writeObject(albums);
            out.flush();
            stream.getFD().sync();
            stream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }




    // Load the previously stored ArrayList (logsList)
    @SuppressWarnings("unchecked")
    public static void loadObject(Context c)
    {

        FileInputStream stream = null;

        try
        {
            stream = c.openFileInput(fileName);
            if (stream == null)
            {
            return;    
            }
            
            ObjectInputStream in = new ObjectInputStream(stream);
            ArrayList<Album> list;
            Object temp;
            temp = in.readObject();
            if (temp != null)
            {
                list = (ArrayList<Album>) temp;
                stream.close();
                albums = list;
            }

        } catch (FileNotFoundException e)
        {
        } catch (OptionalDataException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    
}
