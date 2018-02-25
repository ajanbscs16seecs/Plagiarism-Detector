/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plagiarism.detector;

import java.util.Map;

/**
 *
 * @author Arif
 */
public class TextFile {
    private String tile;
    private String content;
    private Map<String,Integer> frequency;
    
    private double norm;

    public TextFile(String tile, String content) {
        this.tile = tile;
        this.content = content;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public double getNorm() {
        return norm;
    }

    public void setNorm(double norm) {
        this.norm = norm;
    }
    
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Integer> getFrequency() {
        return frequency;
    }

    public void setFrequency(Map<String, Integer> frequency) {
        this.frequency = frequency;
    }

    
    
    @Override
    public String toString() {
        return "TextFile{" + "tile=" + tile + ", content=" + content + '}';
    }
    
    
    
}
