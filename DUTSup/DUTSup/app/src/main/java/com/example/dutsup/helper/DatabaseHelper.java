package com.example.dutsup.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.dutsup.model.Concerne;
import com.example.dutsup.model.Domaine;
import com.example.dutsup.model.Ecoles;
import com.example.dutsup.model.Formation;
import com.example.dutsup.model.Propose;
import com.example.dutsup.model.TypeEcole;
import com.example.dutsup.model.Utilisateur;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG="DatabaseHelper";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="DUTSUP";
    //TABLE NAME
    private static final String TABLE_UTILISATEUR = "utilisateur";
    private static final String TABLE_ECOLE = "ecole";
    private static final String TABLE_FORMATION = "formation";
    private static final String TABLE_DOMAINE = "domaine";
    private static final String TABLE_TYPEECOLE = "typeecole";
    private static final String TABLE_PROPOSE="propose";
    private static final String TABLE_CONCERNE="concerne";
    //COLUMNS NAMES UTILISATEUR
    private static final String UTILISATEUR_NUMUTILISATEUR = "numUtilisateur";
    private static final String UTILISATEUR_NOM = "nomUtilisateur";
    private static final String UTILISATEUR_EMAIL = "emailUtilisateur";
    //COLUMNS NAMES ECOLE
    private static final String ECOLE_NUMECOLE = "numEcole";
    private static final String ECOLE_NOMECOLE = "nomEcole";
    private static final String ECOLE_LOGOECOLE = "logoEcole";
    private static final String ECOLE_NUMTYPE = "numType";
    //COLUMNS NAMES FORMATION
    private static final String FORMATION_NUMFORMATION = "numFormation";
    private static final String FORMATION_NOMFORMATION = "nomFormation";
    private static final String FORMATION_DESCRIPTIFFORM = "descriptifForm";
    //COLUMNS NAMES DOMAINE
    private static final String DOMAINE_NUMDOMAINE = "numDomaine";
    private static final String DOMAINE_NOMDOMAINE = "nomDomaine";
    private static final String DOMAINE_DESCRIPTIFDOMAINE = "descriptifDomaine";
    //COLUMNS NAMES TYPEECOLE
    private static final String TYPEECOLE_NUMTYPE = "numType";
    private static final String TYPEECOLE_NOMTYPE = "nomType";
    //COLUMNS NAMES PROPOSE
    private static final String PROPOSE_NUMECOLE = "numEcole";
    private static final String PROPOSE_NUMFORMATION = "numFormation";
    //COLUMNS NAMES CONCERNE
    private static final String CONCERNE_NUMFORMATION = "numFormation";
    private static final String CONCERNE_NUMDOMAINE = "numDomaine";
    //CREATE TABLE
    private static final String CREATE_TABLE_UTILISATEUR = "CREATE TABLE "+TABLE_UTILISATEUR+" ( "+
            UTILISATEUR_NUMUTILISATEUR+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            UTILISATEUR_NOM+" TEXT,"+
            UTILISATEUR_EMAIL+" TEXT);";
    private static final String CREATE_TABLE_TYPEECOLE = "CREATE TABLE "+TABLE_TYPEECOLE+" ( "+
            TYPEECOLE_NUMTYPE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            TYPEECOLE_NOMTYPE+" TEXT);";
    private static final String CREATE_TABLE_ECOLE = "CREATE TABLE "+TABLE_ECOLE+" ( "+
            ECOLE_NUMECOLE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ECOLE_NOMECOLE+"TEXT,"+
            ECOLE_LOGOECOLE+" TEXT,"+
            ECOLE_NUMTYPE+" INTEGER,"+
            "FOREIGN KEY ("+ECOLE_NUMTYPE+") REFERENCES "+TABLE_TYPEECOLE+"("+TYPEECOLE_NUMTYPE+"));";
    private static final String CREATE_TABLE_FORMATION = "CREATE TABLE "+TABLE_FORMATION+" ( "+
            FORMATION_NUMFORMATION+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            FORMATION_NOMFORMATION+"TEXT,"+
            FORMATION_DESCRIPTIFFORM+"TEXT);";
    private static final String CREATE_TABLE_DOMAINE = "CREATE TABLE "+TABLE_DOMAINE+" ( "+
            DOMAINE_NUMDOMAINE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            DOMAINE_NOMDOMAINE+"TEXT,"+
            DOMAINE_DESCRIPTIFDOMAINE+" TEXT);";
    //CREATE TABLE ASSOCIATION
    private static final String CREATE_TABLE_PROPOSE = "CREATE TABLE "+TABLE_PROPOSE+" ( "+
            PROPOSE_NUMECOLE+" INTEGER,"+
            PROPOSE_NUMFORMATION+" INTEGER,"+
            "PRIMARY KEY ("+PROPOSE_NUMECOLE+","+PROPOSE_NUMFORMATION+"),"+
            "FOREIGN KEY ("+PROPOSE_NUMECOLE+"REFERENCES"+TABLE_ECOLE+"("+ECOLE_NUMECOLE+"),"+
            "FOREIGN KEY ("+PROPOSE_NUMFORMATION+"REFERENCES"+TABLE_FORMATION+"("+FORMATION_NUMFORMATION+");";
    private static final String CREATE_TABLE_CONCERNE = "CREATE TABLE "+TABLE_CONCERNE+" ( "+
            CONCERNE_NUMFORMATION+" INTEGER,"+
            CONCERNE_NUMDOMAINE+" INTEGER,"+
            "PRIMARY KEY ("+CONCERNE_NUMFORMATION+","+CONCERNE_NUMDOMAINE+"),"+
            "FOREIGN KEY ("+CONCERNE_NUMFORMATION+"REFERENCES"+TABLE_FORMATION+"("+FORMATION_NUMFORMATION+"),"+
            "FOREIGN KEY ("+CONCERNE_NUMDOMAINE+"REFERENCES"+TABLE_DOMAINE+"("+DOMAINE_NUMDOMAINE+");";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_UTILISATEUR);
        db.execSQL(CREATE_TABLE_TYPEECOLE);
        db.execSQL(CREATE_TABLE_ECOLE);
        db.execSQL(CREATE_TABLE_DOMAINE);
        db.execSQL(CREATE_TABLE_FORMATION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DOMAINE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ECOLE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FORMATION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TYPEECOLE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_UTILISATEUR);
        onCreate(db);
    }

    //METHODE DOMAINE
    public void createDomaine(Domaine d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(DOMAINE_NOMDOMAINE,d.getNomDomaine());
        value.put(DOMAINE_DESCRIPTIFDOMAINE,d.getDescriptifDomaine());
        db.insert(TABLE_DOMAINE,null,value);
    }
    public Domaine getDomaine(int num){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_DOMAINE+" WHERE"+DOMAINE_NUMDOMAINE+" = "+num;
        Log.e(LOG,selectQuery);
        Cursor c = db.rawQuery(selectQuery,null);
        if(c!=null){c.moveToFirst();}
        Domaine d = new Domaine();
        d.setNomDomaine(c.getString(c.getColumnIndex(DOMAINE_NOMDOMAINE)));
        d.setDescriptifDomaine(c.getString(c.getColumnIndex(DOMAINE_DESCRIPTIFDOMAINE)));
        d.setNumDomaine(c.getInt(c.getColumnIndex(DOMAINE_NUMDOMAINE)));
        return d;
    }
    public List<Domaine> getAllDomaine(){
        List<Domaine> domaines = new ArrayList<Domaine>();
        String selectQuery = "SELECT * FROM "+TABLE_DOMAINE;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Domaine d = new Domaine();
                d.setNumDomaine(c.getInt(c.getColumnIndex(DOMAINE_NUMDOMAINE)));
                d.setDescriptifDomaine(c.getString(c.getColumnIndex(DOMAINE_DESCRIPTIFDOMAINE)));
                d.setNomDomaine(c.getString(c.getColumnIndex(DOMAINE_NOMDOMAINE)));
                domaines.add(d);
            } while (c.moveToNext());
        }
        return domaines;
    }
    public void updateDomaine(Domaine d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(DOMAINE_NOMDOMAINE,d.getNomDomaine());
        value.put(DOMAINE_DESCRIPTIFDOMAINE,d.getDescriptifDomaine());
        db.update(TABLE_DOMAINE,value,DOMAINE_NUMDOMAINE+" = ?",new String[]
                {String.valueOf(d.getNumDomaine())});
    }
    public void deleteDomaine (int num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONCERNE,CONCERNE_NUMDOMAINE+" = ?",new String[]
                {String.valueOf(num)});
        db.delete(TABLE_DOMAINE,DOMAINE_NUMDOMAINE+" = ?",new String[]
                {String.valueOf(num)});
    }
    //METHODE ECOLE
    public void createEcole(Ecoles e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ECOLE_NOMECOLE,e.getNomEcole());
        value.put(ECOLE_LOGOECOLE,e.getLogoEcole());
        value.put(ECOLE_NUMTYPE,e.getNumType());
        db.insert(TABLE_ECOLE,null,value);
    }
    public Ecoles getEcole (int num){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_ECOLE+" WHERE"+ECOLE_NUMECOLE+" = "+num;
        Log.e(LOG,selectQuery);
        Cursor c = db.rawQuery(selectQuery,null);
        if(c!=null){c.moveToFirst();}
        Ecoles e = new Ecoles();
        e.setNumEcole(c.getInt(c.getColumnIndex(ECOLE_NUMECOLE)));
        e.setNomEcole(c.getString(c.getColumnIndex(ECOLE_NOMECOLE)));
        e.setLogoEcole(c.getString(c.getColumnIndex(ECOLE_LOGOECOLE)));
        e.setNumType(c.getInt(c.getColumnIndex(ECOLE_NUMTYPE)));
        return e;
    }
    public List<Ecoles> getAllEcole(){
        List<Ecoles> ecoles = new ArrayList<Ecoles>();
        String selectQuery = "SELECT * FROM "+TABLE_ECOLE;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Ecoles e = new Ecoles();
                e.setNumEcole(c.getInt(c.getColumnIndex(ECOLE_NUMECOLE)));
                e.setNomEcole(c.getString(c.getColumnIndex(ECOLE_NOMECOLE)));
                e.setLogoEcole(c.getString(c.getColumnIndex(ECOLE_LOGOECOLE)));
                e.setNumType(c.getInt(c.getColumnIndex(ECOLE_NUMTYPE)));
                ecoles.add(e);
            } while (c.moveToNext());
        }
        return ecoles;
    }
    public void updateEcole(Ecoles e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ECOLE_NOMECOLE,e.getNomEcole());
        value.put(ECOLE_LOGOECOLE,e.getLogoEcole());
        value.put(ECOLE_NUMTYPE,e.getNumType());
        db.update(TABLE_ECOLE,value,ECOLE_NUMECOLE+" = ?",new String[]
                {String.valueOf(e.getNumEcole())});
    }
    public void deleteEcole(int num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROPOSE,PROPOSE_NUMECOLE+" = ?",new String[]
                {String.valueOf(num)});
        db.delete(TABLE_ECOLE,ECOLE_NUMECOLE+" = ?",new String[]
                {String.valueOf(num)});

    }
    //METHODE FORMATION
    public void createFormation(Formation f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(FORMATION_NOMFORMATION,f.getNomFormation());
        value.put(FORMATION_DESCRIPTIFFORM,f.getDescriptifFormation());
        db.insert(TABLE_FORMATION,null,value);
    }
    public Formation getFormation (int num){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_FORMATION+" WHERE"+FORMATION_NUMFORMATION+
                " = "+num;
        Log.e(LOG,selectQuery);
        Cursor c = db.rawQuery(selectQuery,null);
        if(c!=null){c.moveToFirst();}
        Formation f = new Formation();
        f.setNumFormation(c.getInt(c.getColumnIndex(FORMATION_NUMFORMATION)));
        f.setNomFormation(c.getString(c.getColumnIndex(FORMATION_NOMFORMATION)));
        f.setDescriptifFormation(c.getString(c.getColumnIndex(FORMATION_DESCRIPTIFFORM)));
        return f;
    }
    public List<Formation> getAllFormation(){
        List<Formation> formations = new ArrayList<Formation>();
        String selectQuery = "SELECT * FROM "+TABLE_FORMATION;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Formation f = new Formation();
                f.setNumFormation(c.getInt(c.getColumnIndex(FORMATION_NUMFORMATION)));
                f.setNomFormation(c.getString(c.getColumnIndex(FORMATION_NOMFORMATION)));
                f.setDescriptifFormation(c.getString(c.getColumnIndex(FORMATION_DESCRIPTIFFORM)));
                formations.add(f);
            } while (c.moveToNext());
        }
        return formations;
    }
    public void updateFormation(Formation f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(FORMATION_NOMFORMATION,f.getNomFormation());
        value.put(FORMATION_DESCRIPTIFFORM,f.getDescriptifFormation());
        db.update(TABLE_FORMATION,value,FORMATION_NUMFORMATION+" = ?",new String[]
                {String.valueOf(f.getNumFormation())});
    }
    public void deleteFormation(int num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONCERNE,CONCERNE_NUMFORMATION+" = ?",new String[]
                {String.valueOf(num)});
        db.delete(TABLE_PROPOSE,CONCERNE_NUMFORMATION+" = ?",new String[]
                {String.valueOf(num)});
        db.delete(TABLE_FORMATION,FORMATION_NUMFORMATION+" = ?",new String[]
                {String.valueOf(num)});
    }
    //METHODE TYPEECOLE
    public void createTypeEcole(TypeEcole t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(TYPEECOLE_NOMTYPE,t.getNomType());
        db.insert(TABLE_TYPEECOLE,null,value);
    }
    public TypeEcole getTypeEcole (int num){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_TYPEECOLE+" WHERE"+TYPEECOLE_NUMTYPE+" = "+num;
        Log.e(LOG,selectQuery);
        Cursor c = db.rawQuery(selectQuery,null);
        if(c!=null){c.moveToFirst();}
        TypeEcole t = new TypeEcole();
        t.setNomType(c.getString(c.getColumnIndex(TYPEECOLE_NOMTYPE)));
        t.setNumType(c.getInt(c.getColumnIndex(TYPEECOLE_NUMTYPE)));
        return t;
    }
    public List<TypeEcole> getAllTypeEcole(){
        List<TypeEcole> typeEcoles = new ArrayList<TypeEcole>();
        String selectQuery = "SELECT * FROM "+TABLE_TYPEECOLE;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                TypeEcole t = new TypeEcole();
                t.setNomType(c.getString(c.getColumnIndex(TYPEECOLE_NOMTYPE)));
                t.setNumType(c.getInt(c.getColumnIndex(TYPEECOLE_NUMTYPE)));
                typeEcoles.add(t);
            } while (c.moveToNext());
        }
        return typeEcoles;
    }
    public void updateTypeEcole(TypeEcole t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(TYPEECOLE_NOMTYPE,t.getNomType());
        db.update(TABLE_TYPEECOLE,value,TYPEECOLE_NUMTYPE+" = ?",new String[]
                {String.valueOf(t.getNumType())});
    }
    public void deleteTypeEcole(int num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TYPEECOLE,TYPEECOLE_NUMTYPE+" = ?",new String[]
                {String.valueOf(num)});
    }
    //METHODE UTILISATEUR
    public void createUtilisateur(Utilisateur u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(UTILISATEUR_NOM,u.getNomUtilisateur());
        value.put(UTILISATEUR_EMAIL,u.getEmailUtilisateur());
        db.insert(TABLE_UTILISATEUR,null,value);
    }
    public Utilisateur getUtilisateur (int num){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_UTILISATEUR+" WHERE"+UTILISATEUR_NUMUTILISATEUR+
                " = "+num;
        Log.e(LOG,selectQuery);
        Cursor c = db.rawQuery(selectQuery,null);
        if(c!=null){c.moveToFirst();}
        Utilisateur u = new Utilisateur();
        u.setNumUtilisateur(c.getInt(c.getColumnIndex(UTILISATEUR_NUMUTILISATEUR)));
        u.setNomUtilisateur(c.getString(c.getColumnIndex(UTILISATEUR_NOM)));
        u.setEmailUtilisateur(c.getString(c.getColumnIndex(UTILISATEUR_EMAIL)));
        return u;
    }
    public List<Utilisateur> getAllUtilisateur(){
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        String selectQuery = "SELECT * FROM "+TABLE_UTILISATEUR;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Utilisateur u = new Utilisateur();
                u.setNumUtilisateur(c.getInt(c.getColumnIndex(UTILISATEUR_NUMUTILISATEUR)));
                u.setNomUtilisateur(c.getString(c.getColumnIndex(UTILISATEUR_NOM)));
                u.setEmailUtilisateur(c.getString(c.getColumnIndex(UTILISATEUR_EMAIL)));
                utilisateurs.add(u);
            } while (c.moveToNext());
        }
        return utilisateurs;
    }
    public void updateUtilisateur(Utilisateur u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(UTILISATEUR_NOM,u.getNomUtilisateur());
        value.put(UTILISATEUR_EMAIL,u.getEmailUtilisateur());
        db.update(TABLE_UTILISATEUR,value,UTILISATEUR_NUMUTILISATEUR+" = ?",new
                String[]{String.valueOf(u.getNumUtilisateur())});
    }
    public void deleteUtilisateur(int num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_UTILISATEUR,UTILISATEUR_NUMUTILISATEUR+" = ?",new String[]
                {String.valueOf(num)});
    }
    //METHODE PROPOSE
    public void createPropose(Propose p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(PROPOSE_NUMECOLE,p.getNumEcole());
        value.put(PROPOSE_NUMFORMATION,p.getNumFormation());
        db.insert(TABLE_PROPOSE,null,value);
    }
    public List<Propose> getPropose(int num, String nomColonne){
        List<Propose> proposes = new ArrayList<Propose>();
        String selectQuery = "SELECT * FROM "+TABLE_PROPOSE+" WHERE "+nomColonne+" = "+num;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Propose p = new Propose();
                p.setNumEcole(c.getInt(c.getColumnIndex(PROPOSE_NUMECOLE)));
                p.setNumFormation(c.getInt(c.getColumnIndex(PROPOSE_NUMFORMATION)));
                proposes.add(p);
            } while (c.moveToNext());
        }
        return proposes;
    }
    public List<Propose> getAllPropose(){
        List<Propose> proposes = new ArrayList<Propose>();
        String selectQuery = "SELECT * FROM "+TABLE_PROPOSE;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Propose p = new Propose();
                p.setNumEcole(c.getInt(c.getColumnIndex(PROPOSE_NUMECOLE)));
                p.setNumFormation(c.getInt(c.getColumnIndex(PROPOSE_NUMFORMATION)));
                proposes.add(p);
            } while (c.moveToNext());
        }
        return proposes;
    }
    public void deletePropose(int numEcole, int numFormation){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROPOSE,PROPOSE_NUMECOLE+" = ? AND "+PROPOSE_NUMFORMATION+
                " = ?", new String[]{String.valueOf(numEcole),String.valueOf(numFormation)});
    }
    //METHODE CONCERNE
    public void createConcerne(Concerne c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(CONCERNE_NUMDOMAINE,c.getNumDomaine());
        value.put(CONCERNE_NUMFORMATION,c.getNumFormation());
        db.insert(TABLE_CONCERNE,null,value);
    }
    public List<Concerne> getConcerne (int num,String nomColonne){
        List<Concerne> concernes = new ArrayList<Concerne>();
        String selectQuery = "SELECT * FROM "+TABLE_CONCERNE+" WHERE "+nomColonne+" = "+num;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Concerne co = new Concerne();
                co.setNumDomaine(c.getInt(c.getColumnIndex(CONCERNE_NUMDOMAINE)));
                co.setNumFormation(c.getInt(c.getColumnIndex(CONCERNE_NUMFORMATION)));
                concernes.add(co);
            } while (c.moveToNext());
        }
        return concernes;
    }
    public List<Concerne> getAllConcerne(){
        List<Concerne> concernes = new ArrayList<Concerne>();
        String selectQuery = "SELECT * FROM "+TABLE_CONCERNE;
        Log.e(LOG,selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do {
                Concerne co = new Concerne();
                co.setNumDomaine(c.getInt(c.getColumnIndex(CONCERNE_NUMDOMAINE)));
                co.setNumFormation(c.getInt(c.getColumnIndex(CONCERNE_NUMFORMATION)));
                concernes.add(co);
            } while (c.moveToNext());
        }
        return concernes;
    }
    public void deleteConcerne(int numDomaine, int numFormation){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONCERNE,CONCERNE_NUMDOMAINE+" = ? AND "+CONCERNE_NUMFORMATION+
                " = ?", new String[]{String.valueOf(numDomaine),String.valueOf(numFormation)});
    }
    //CLOSE DATABASE
    public synchronized void close() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db!=null&&db.isOpen()){
            db.close();
        }
    }
}
