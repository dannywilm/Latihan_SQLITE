package com.project.ank.latihandatabase.DB_Class;

/**
 * Created by wilim on 12/16/2017.
 */

public class GetSetClass {

    private String artistName, artistGender, artistDOB;
    private int artistId;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistGender() {
        return artistGender;
    }

    public void setArtistGender(String artistGender) {
        this.artistGender = artistGender;
    }

    public String getArtistDOB() {
        return artistDOB;
    }

    public void setArtistDOB(String artistDOB) {
        this.artistDOB = artistDOB;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
