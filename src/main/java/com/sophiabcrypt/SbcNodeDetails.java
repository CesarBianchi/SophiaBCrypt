/*
 * Copyright (C) 2018 CesarBianchi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package sophiabcrypt;

/**
 * This class its used for control details at tree nodes of the main menu
  * Control max file lenght, and others!
 * @author CesarBianchi
 * @since November/2018
 */
public class SbcNodeDetails {
    private String nodeName = new String();
    private String fullPathOfNode = new String();

    /**
     * 
     * This is a constructor method of class
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public SbcNodeDetails() {
        
    }

    /**
     * This is a constructor method of class (overload)
     * @param name The name of the especific node
     * @param Path The absolute path relative of node 
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public SbcNodeDetails(String name, String Path) {
        this.setNodeName(name);
        this.setfullPathOfNode(Path);
    }
    
    /**
     * This method override returns the node name in String
     * Its necessary for use in JTree
     * @return The node name in String format
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public String toString(){
        return this.getNodeName();
    }
    
    /**
     * Gets the node name (description)
     * @return The node name
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public String getNodeName() {
        return this.nodeName;
    }
    
    /**
     * Gets the full path of the node
     * @return The node path (in storage)
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public String getfullPathOfNode(){
        return this.fullPathOfNode;
    }
    
    /**
     * Sets the node name
     * @param Name The name of the node
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public void setNodeName(String Name) {
        this.nodeName = Name;
    }
    
    /**
     * Sets the full path of the node
     * @param path The full path of node
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public void setfullPathOfNode(String path){
        this.fullPathOfNode = path;
    }
    
    
}
