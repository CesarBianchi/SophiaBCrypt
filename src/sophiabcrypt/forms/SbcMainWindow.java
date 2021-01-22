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

package sophiabcrypt.forms;
import java.awt.Cursor;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import sophiabcrypt.SbcNodeDetails;
import sophiabcrypt.language.SbcDictionaryBase;
import sophiabcrypt.language.SbcDictionarySentence;

/**
 * This class is a main GUI for SophiaBCrypt
 * It shows a graphical interface for the user to select files or folders 
 * and to be able to choose the operations of Encrypt or Decrypt
 * @author CesarBianchi
 * @see SbcLanguageWindow
 * @since June/2018
 */
public class SbcMainWindow extends javax.swing.JFrame {
    private String cLanguage = new String();
    private String cLangFileName = new String();
    private String cParamFileName = new String();
    private ArrayList<SbcDictionarySentence> SentencesInMemory = new ArrayList<SbcDictionarySentence>();
    private String cPathSelected = new String();
    private ArrayList<String> afiles = new ArrayList<String>();
    
    /**
     * This method create a new Main Window
     * @author CesarBianchi
     * @since Aug/2018
    */
    public SbcMainWindow() {
        setSbcIcon();
        initComponents();
    }

    /**
     * This method show de window interface with all sentences translated
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     */
    public void init(){
        this.setTranslates();
        this.loadRoots();
        this.show();
    }
    
    /**
     * This method load all root units from machine (MacOs, Windows or Linux) and input this roots in JTree
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void loadRoots() {
        File[] units = File.listRoots();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        
        for (int nI = 0; nI < units.length; nI++){
            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new SbcNodeDetails(units[nI].getPath(),units[nI].getPath()));
            root.add(rootNode);
            
            DefaultMutableTreeNode Aux = new DefaultMutableTreeNode(new SbcNodeDetails("..",units[nI].getPath()));
            rootNode.add(Aux);
        }
        
        DefaultTreeModel model = new DefaultTreeModel(root);
        this.jTree1.setModel(model);
        
    }
    
    /**
     * This method load all content present in node referenced by input param and set in new node.
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void loadFilesAndFolders(DefaultMutableTreeNode node,String cPathSelected,boolean isSubDir){
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        String fullPath = new String();
        File rootfile = new File( cPathSelected );
        
        if (rootfile.isDirectory()){
            node.setUserObject(rootfile);
            File[] ListOfFiles = rootfile.listFiles();
            
            for (int nI = 0; nI <= ListOfFiles.length-1; nI++){

                fullPath = this.buildFullPath(cPathSelected,ListOfFiles[nI].getName());

                if (ListOfFiles[nI].isDirectory()){
                    DefaultMutableTreeNode directory = new DefaultMutableTreeNode(new SbcNodeDetails(ListOfFiles[nI].getName(),fullPath));
                    node.add(directory);

                    DefaultMutableTreeNode Aux = new DefaultMutableTreeNode(new SbcNodeDetails("..",cPathSelected));
                    directory.add(Aux);

                } else {
                    DefaultMutableTreeNode file = new DefaultMutableTreeNode(new SbcNodeDetails(ListOfFiles[nI].getName(),fullPath));
                    node.add(file);
                }

            }
        
            DefaultTreeModel model = new DefaultTreeModel(node);
            this.jTree1.setModel(model);
        }
        
        this.verifyNode(node,cPathSelected);        
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    /**
     * This method is called at click or expand node in JTree
     * Load all sub-content by according the node selected
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void NodeSelected() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.jTree1.getLastSelectedPathComponent();
        String cFullPath = new String("");
        
        if (node == null){
            return;
        } else {
        
            SbcNodeDetails NodeDetails = (SbcNodeDetails) node.getUserObject();
            cFullPath = NodeDetails.getfullPathOfNode();

            this.loadFilesAndFolders(node,cFullPath,true);
        }
        
    }
    
    /**
     * Verify if node content have back option "..˜
     * Load all sub-content by according the node selected
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void verifyNode(DefaultMutableTreeNode node, String cFullPath) {
        boolean locatedBackOpt = false;
        
        if (!cFullPath.equals(this.getBarByOs())){
            SbcNodeDetails sbcNode = new SbcNodeDetails();
            DefaultMutableTreeNode nodeDetail = new DefaultMutableTreeNode();

            int qtdChilds = this.jTree1.getModel().getChildCount(node);
            for (int nI = 0; nI < qtdChilds; nI ++ ){
                nodeDetail = (DefaultMutableTreeNode) this.jTree1.getModel().getChild(node,nI);
                sbcNode = (SbcNodeDetails) nodeDetail.getUserObject();
                if (sbcNode.getNodeName().equals("..")){
                    locatedBackOpt = true;
                    break;
                }
            }
            
            if (!locatedBackOpt){
                File arq = new File(cFullPath);
                if (arq.isDirectory()){
                    String cPrevPath = this.prevPath(cFullPath);
                    DefaultMutableTreeNode backOpt = new DefaultMutableTreeNode(new SbcNodeDetails("..",cPrevPath));
                    node.insert(backOpt, 0);
                    
                    DefaultTreeModel model = new DefaultTreeModel(node);
                    this.jTree1.setModel(model);
                }
            }
        }
    }
    
    /**
     * Build a absolute path by according the input params
     * @param cPathSelect The path of root
     * @param name The name of selected node
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private String buildFullPath(String cPathSelected, String name) {
        String cReturn = new String();
        String cBarra = this.getBarByOs();
        
        if (!cPathSelected.substring(cPathSelected.length()-1).equals(cBarra)){
            if (!name.substring(0,0).equals(cBarra)){
                cReturn = cPathSelected + cBarra + name;
            } else {
                cReturn = cPathSelected + name;
            }    
        } else {
            cReturn = cPathSelected + name;        
        }
        return cReturn;
    }
    
    /**
     * Tokenize a specific absolut path and return a ArrayList
     * @param cPath The path to be tokenize
     * @return The tokenized path in arraylist
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private ArrayList tokenizePath(String cPath){
        ArrayList<String> aReturn = new ArrayList<String>();
        int cBarraLen = 0;
        String cBarra = this.getBarByOs();
        String cAux = new String("");
        
        cBarraLen = cBarra.length();
        
        if (!cPath.equals("")){
            for (int nI = 0; nI < cPath.length(); nI++){
                if (cPath.substring(nI,nI+cBarraLen).equals(cBarra)){
                    if (!cAux.equals("")){
                        aReturn.add(cAux);
                        cAux = "";
                    }
                } else {
                    cAux = cAux + cPath.substring(nI,nI+1);
                }    
            }
            if (!cAux.equals("")){
                aReturn.add(cAux);
                cAux = "";
            }
        }        
        
        return aReturn;
    }
    
    /**
     * Returns the path separator by according of Operational System
     * @return Char of path separator
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private String getBarByOs(){        
        String cBarra = File.separator;        
        return cBarra;
    }
    
    /**
     * Returns the a new path after erase the last directory of input path
     * @param cPath The path to be cut the last directory
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private String prevPath(String cPath){
        String cReturn = new String("");
        ArrayList pathTokenized = this.tokenizePath(cPath);
        String cBarra = this.getBarByOs();
        
        for (int nI = 0; nI < pathTokenized.size()-1;nI++){
            cReturn = cReturn + cBarra + pathTokenized.get(nI);
        }
        if (cReturn.equals("")){
            cReturn = cBarra;
        }
        return cReturn;
    }
    
    /**
     * Returns the main path defined (root path)
     * @return The main path of the root path
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public String getPathSelected(){
        return this.cPathSelected;
    }
    
    /**
     * Sets the main path defined (root path)
     * @param cPath The main path of the root path
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public void setPathSelected(String cPath){
        if (cPath.equals("")){
            this.cPathSelected = File.separator;
        } else {
            this.cPathSelected = cPath;
        }     
    }
    
    /**
     * Sets the language file name used by translation features
     * @param cSentenceFile The name of the language file (usually LanguageFile.xml)
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public void setLanguageFileName(String cSentenceFile) {
        this.cLangFileName = cSentenceFile;
    }
    
    /**
     * Sets the Param file name used by aplication
     * @param cParamFile The name of the param file
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    public void setParamFileName(String cParamFile) {
        this.cParamFileName = cParamFile;
    }
    
    /**
     * Add a new path to Process List in the right side of the main window
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void addPathToProcess() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.jTree1.getLastSelectedPathComponent();
                
        if (node == null){
            JOptionPane.showMessageDialog(rootPane,"Selecione ao menos um arquivo ou diretorio para adicionar a lista.", "Nenhum diretorio ou arquivo selecionado", JOptionPane.WARNING_MESSAGE, null);
        } else {
            SbcNodeDetails NodeDetails = (SbcNodeDetails) node.getUserObject();
            String cFullPath = NodeDetails.getfullPathOfNode();            
            
            DefaultListModel list = (DefaultListModel) this.jList2.getModel();            
            list.addElement(new String(cFullPath));
            this.jList2.setModel(list);

        }          
    }

    /**
     * Remove a specific path to Process List in the right side of the main window
     * @author CesarBianchi
     * @since November/2018
     * @version 1.03.3
    */
    private void removePathToProcess() {
        int nInd = this.jList2.getSelectedIndex();
        
        if (nInd >= 0){
            DefaultListModel list = (DefaultListModel) this.jList2.getModel();            
            list.remove(nInd);
            this.jList2.setModel(list);
        } else {
            JOptionPane.showMessageDialog(rootPane,"Selecione ao menos um item da lista para remover.", "Nenhum item selecionado", JOptionPane.WARNING_MESSAGE, null);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<String>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SophiaBCrypt - Criptografador de arquivos");
        setName("MainFrame"); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sophiabcrypt/images/v5_unlock_blue_small.png"))); // NOI18N
        jButton2.setText("Descriptografar");
        jButton2.setToolTipText("Descriptografa os arquivos ou pastas selecionados a esquerda");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setIconTextGap(2);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sophiabcrypt/images/v5_lock_blue_small.png"))); // NOI18N
        jButton1.setText("    Criptografar");
        jButton1.setToolTipText("Criptografa os arquivos ou pastas selecionados a esquerda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList2.setModel(new DefaultListModel ());
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList2);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
                jTree1TreeWillExpand(evt);
            }
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
            }
        });
        jScrollPane3.setViewportView(jTree1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sophiabcrypt/images/to_right_blue_small.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sophiabcrypt/images/to_left_blue_small.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(158, 158, 158)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jMenu1.setText("Arquivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Criptografar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Descriptografar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Sair");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Opções");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_NUMPAD1, 0));
        jMenuItem5.setText("Idioma");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_NUMPAD2, 0));
        jMenuItem6.setText("Traduzir Sentencas");
        jMenuItem6.setToolTipText("");
        jMenuItem6.setActionCommand("Traduzir Sentenças");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Ajuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Sobre");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            SbcExitWindow exitDiag = new SbcExitWindow();
            exitDiag.setLocationRelativeTo(null);
            exitDiag.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            exitDiag.setResizable(false);
            exitDiag.setSentences(this.getLanguage(),this.SentencesInMemory);
            exitDiag.init();
    }                                          

    /**
     * 
     * This method invokes a Encrypt Engine and process all selected files and folders
     * @author CesarBianchi
     * @since Aug/2018
    */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.pressCrypt();
    }                                        

    /**
     * 
     * This method invokes a Decrypt Engine and process all selected files and folders
     * @author CesarBianchi
     * @since Aug/2018
    */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.pressDecrypt();
    }                                        
   
    /**
     * 
     * This method invokes a Encrypt Engine and process all selected files and folders
     * Its used from Ahead MenuItem
     * @author CesarBianchi
     * @since Aug/2018
    */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.pressCrypt();
    }                                          

    /**
     * 
     * This method invokes a Decrypt Engine and process all selected files and folders
     * @author CesarBianchi
     * Its used from Ahead MenuItem
     * @since Aug/2018
    */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.pressDecrypt();
    }                                          

    /**
     * 
     * This method invokes "The About Window"
     * @author CesarBianchi
     * @since Aug/2018
    */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        SbcAboutWindow SbcAbout = new SbcAboutWindow();
        SbcAbout.SetSentences(this.getLanguage(),this.SentencesInMemory);
        SbcAbout.init();
    }                                          

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        SbcLanguageWindow SbcLangWin = new SbcLanguageWindow();
        SbcLangWin.fromLoad(false);
        SbcLangWin.setSentences(this.SentencesInMemory);
        SbcLangWin.setParamFileName(this.cParamFileName);
        SbcLangWin.LoadLanguageWindow();        
    }                                          

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        SbcSentencesWindow SbcSentWin = new SbcSentencesWindow();
        SbcSentWin.init(this.getLanguage(),this.SentencesInMemory,this.cLangFileName);
        
    }                                          

    private void jTree1TreeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {                                      
        this.jTree1.setSelectionPath(evt.getPath());
        this.NodeSelected();
    }                                     

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {                                    
        if (evt.getClickCount() == 2) {
            this.NodeSelected();  
        }
    }                                   

    private void jTree1TreeExpanded(javax.swing.event.TreeExpansionEvent evt) {                                    
        /*NOTHING*/
    }                                   

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.addPathToProcess();
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.removePathToProcess();
    }                                        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SbcMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SbcMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SbcMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SbcMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SbcMainWindow().setVisible(true);
            }
        });
    }

    /**
     * 
     * This method prepare enviroment for init Encrypt Process
     * Its involke "The password Window" 
     * @author CesarBianchi
     * @since Aug/2018
    */
    private void pressCrypt(){
        String cOper = new String();
        DefaultListModel cPaths = (DefaultListModel) this.jList2.getModel();            
        
        cOper = "ENC";
        SbcPswWindow pswDiag = new SbcPswWindow();  
        pswDiag.setLocationRelativeTo(null);
        pswDiag.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pswDiag.setResizable(false);
        pswDiag.setSentences(this.getLanguage(),this.SentencesInMemory);
        pswDiag.setOper(cOper);
        pswDiag.setPaths(cPaths);
        pswDiag.init();
        
        this.jList2.setModel(new DefaultListModel());
    }
    
    /**
     * 
     * This method prepare enviroment for init Decrypt Process
     * Its involke "The password Window" 
     * @author CesarBianchi
     * @since Aug/2018
    */
    private void pressDecrypt(){
        String cOper = new String();
        DefaultListModel cPaths = (DefaultListModel) this.jList2.getModel();            
        
        cOper = "DEC";
        SbcPswWindow pswDiag = new SbcPswWindow();
        pswDiag.setLocationRelativeTo(null);
        pswDiag.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pswDiag.setResizable(false);
        pswDiag.setSentences(this.getLanguage(),this.SentencesInMemory);
        pswDiag.setOper(cOper);
        pswDiag.setPaths(cPaths);    
        pswDiag.init(); 
        
        this.jList2.setModel(new DefaultListModel());
    }

    /**
     * Set the main application icon
     * @author CesarBianchi
     * @since Aug/2018
    */
    public void setSbcIcon(){
        
    }
    
    /**
     * This methods sets the language defined by user for use in translations
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     */
    private void setLanguage(String br) {
        this.cLanguage = br;
    }
    
    /**
     * This methods get the language defined by user
     * @author CesarBianchi
     * @since October/2018
     * @return cLanguage The language defined by user
     * @version 1.03.1
    */
    private String getLanguage() {
        return this.cLanguage;
    }
    
    /**
     * Set Sentences in Memory before load xml language file
     * @author CesarBianchi
     * @since October/2018
     * @param cLang String with language defined by user
     * @param Sentences ArrayOfList with all sentences loaded from xml language file
     * @version 1.03.1
     */
    public void setSentences(String cLang,ArrayList<SbcDictionarySentence> Sentences) {
        this.setLanguage(cLang);
        this.SentencesInMemory = Sentences;
    }
    
    /**
     * This method sets all words to language defined by user
     * @author CesarBianchi
     * @since October/2018
     * @version 1.03.1
     */
    private void setTranslates() {
        SbcDictionaryBase Dictionary = new SbcDictionaryBase(this.getLanguage());
        Dictionary.setSentenceList(this.SentencesInMemory);
        
        this.jMenu1.setText(Dictionary.getTranslation("0001"));
        this.jMenuItem2.setText(Dictionary.getTranslation("0002"));
        this.jMenuItem3.setText(Dictionary.getTranslation("0003"));
        this.jMenuItem4.setText(Dictionary.getTranslation("0004"));                
        this.jMenu2.setText(Dictionary.getTranslation("0005"));
        this.jMenuItem1.setText(Dictionary.getTranslation("0006"));        
        this.jButton1.setText(Dictionary.getTranslation("0002"));
        this.jButton2.setText(Dictionary.getTranslation("0003"));        
        this.jMenu3.setText(Dictionary.getTranslation("0025"));
        this.jMenuItem5.setText(Dictionary.getTranslation("0026"));
        this.jMenuItem6.setText(Dictionary.getTranslation("0027"));
        
        
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTree jTree1;
    // End of variables declaration                   

    

}
