/*    */ package test;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.enums.DataBaseEnum;
/*    */ import com.tianshouzhi.autopro.util.DataBaseMetaInfoUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TestDataBaseMetaInfoUtil
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 16 */     DBConnectionConfig dbConfig = new DBConnectionConfig("jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8", DataBaseEnum.getDefault().name(), "root", "root");
/* 17 */     List dbTables = DataBaseMetaInfoUtil.getDBTables(dbConfig);
/*    */     Iterator localIterator4;
/* 18 */     for (Iterator localIterator1 = dbTables.iterator(); localIterator1.hasNext(); 
/* 32 */       localIterator4.hasNext())
/*    */     {
/* 18 */       DBTable dbTable = (DBTable)localIterator1.next();
/* 19 */       System.out.println("-----------" + dbTable.getTableName() + "------------------");
/* 20 */       List oneToManyList = dbTable.getOneToManyList();
/* 21 */       System.out.println("--oneToManyList--");
/* 22 */       for (DBTable dbTable2 : oneToManyList) {
/* 23 */         System.out.println(dbTable2.getTableName());
/*    */       }
/* 25 */       System.out.println("--manyToOneList--");
/* 26 */       List manyToOneList = dbTable.getManyToOneList();
/* 27 */       for (DBTable dbTable3 : manyToOneList) {
/* 28 */         System.out.println(dbTable3.getTableName());
/*    */       }
/* 30 */       System.out.println("--manyToManyList--");
/* 31 */       List manyToManyList = dbTable.getManyToManyList();
/* 32 */       localIterator4 = manyToManyList.iterator(); continue; DBTable dbTable4 = (DBTable)localIterator4.next();
/* 33 */       System.out.println(dbTable4.getTableName());
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestDataBaseMetaInfoUtil
 * JD-Core Version:    0.6.2
 */