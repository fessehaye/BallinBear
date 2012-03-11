package model;
import java.util.ArrayList;




public class Album{
    private static String albumName;
    private ArrayList<Photo> photos;
    
    
    // Constructor only needs name
    @SuppressWarnings("static-access")
    public Album(String albumName) {
        this.albumName = albumName;
        this.photos = new ArrayList<Photo>();
    }
    
    
    
    public static String getAlbumName() {
        return albumName;
    }
    
    public void setAlbumName(String albumName)  {
        Album.albumName = albumName;
    }
    
    public ArrayList<Photo> getPhotos() {
        return photos;
    }
    
    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }
    
    
    public Photo getPhoto(int i){
        return photos.get(i);
    }
    
    public void addPhoto(Photo p){
        photos.add(p);
    }
    
    public void deletePhoto(int i){
        photos.remove(i);
    }
    
    public void updatePhoto(int i, Photo p){
        photos.set(i, p);
    }

}
