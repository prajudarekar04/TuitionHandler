package utility;

import java.sql.*;

public class DB
{
    Connection con;

    public PreparedStatement ptradd,ptrmod,ptrdel,ptrdis,ptrser,ptrcnt;

    public PreparedStatement pcladd,pclmod,pcldel,pcldis,pclser,pclcnt;

    public PreparedStatement pctadd,pctmod,pctdel,pctdis,pctser,pctcnt;

    public PreparedStatement pcoadd,pcomod,pcodel,pcodis,pcoser,pcosernm,pcocnt;

    public PreparedStatement pbtadd,pbtmod,pbtdel,pbtdis,pbtdisnm,pbtser,pbtsernm,pbtcnt;

    public PreparedStatement preadd,predel,predis,preser,predtsum1,predtsum2,precqsum,precqsumpsl,precqsumrpl,precasum,precnt,prestsum,preserst;

    public PreparedStatement potadd,potdel,potdtrec,potdtexp,potsumdtrec,potsumdtexp,potcasum,potcqsum,potcqsumpsl,potcqsumrpl,potcqdtsumpsl,potcqdtsumrpl,potdtrecexp;

    public PreparedStatement pstadd,pstmod,pstdel,pstdis,pstdisnm,pstser,pstcnt,pstsertr,pstsernm,pstserbt,pstsercl,pstserco,pstserct,pstserno,pstfees,pstupdfee,pstserbalfee;

    ResultSet rs;

    public DB() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/realsoft","root","admin@123");

        // Trade Queries

        // Query of adding trade
        ptradd=con.prepareStatement("insert into Trade values(?,?,?)");

        // Query of deleting trade id wise
        ptrmod=con.prepareStatement("update Trade set trname=? where trid=? AND trstate=1");

        // Query of displaying all trades
        ptrdel=con.prepareStatement("update Trade set trstate=0 where trid=?");
        
        ptrdis=con.prepareStatement("select * from Trade where trstate=1");

        ptrser=con.prepareStatement("select * from Trade where trid=?");

        ptrcnt=con.prepareStatement("select COUNT(trid) from Trade");


        //College Queries
        
        pcladd=con.prepareStatement("insert into College values(?,?,?)");

        pclmod=con.prepareStatement("update College set clname=? where clid=? AND clstate=1");

        pcldel=con.prepareStatement("update College set clstate=0 where clid=?");

        pcldis=con.prepareStatement("select * from College where clstate=1");

        pclser=con.prepareStatement("select * from College where clid=?");

        pclcnt=con.prepareStatement("select COUNT(clid) from College");

        
        //City Queries

        pctadd=con.prepareStatement("insert into City values(?,?,?)");

        pctmod=con.prepareStatement("update City set ctname=? where ctid=? AND ctstate=1");

        pctdel=con.prepareStatement("update City set ctstate=0 where ctid=?");

        pctdis=con.prepareStatement("select * from City where ctstate=1");

        pctser=con.prepareStatement("select * from City where ctid=?");

        pctcnt=con.prepareStatement("select COUNT(ctid) from City");

        // Course Queries

        pcoadd = con.prepareStatement("insert into Course values(?,?,?,?,?)");

        pcomod = con.prepareStatement("update Course set coname=?,codfee=?,corfee=? where coid=? and costate=1");

        pcodel = con.prepareStatement("update Course set costate=0 where coid=?");

        pcodis = con.prepareStatement("select * from Course where costate=1");

        pcoser = con.prepareStatement("select * from Course where coid=?");

        pcosernm = con.prepareStatement("select * from Course where coname=?");

        pcocnt = con.prepareStatement("select COUNT(coid) from Course");

        //Batch Queries

        pbtadd=con.prepareStatement("insert into Batch values(?,?,?,?,?,?)");

        pbtmod=con.prepareStatement("update Batch set btname=? , btstdate=? , bteddate=?,bttime=? where btid=? AND btstate=1");

        pbtdel=con.prepareStatement("update Batch set btstate=0 where btid=?");

        pbtdis=con.prepareStatement("select * from Batch where btstate=1");  

        pbtdisnm=con.prepareStatement("select btname from Batch where btstate=1"); 


        pbtser=con.prepareStatement("select * from Batch where btid=?");

        pbtsernm=con.prepareStatement("select * from Batch where btname=?");
    
        pbtcnt=con.prepareStatement("select COUNT(btid) from Batch");

        //Receipt Queries

        preadd=con.prepareStatement("insert into Receipt values(?,?,?,?,?,?,?,1)");

        predel=con.prepareStatement("update Receipt set restate=0 where reno=?");

        predis=con.prepareStatement("select * from Receipt where rerollno=? && restate=1");

        preser=con.prepareStatement("select * from Receipt where reno=? AND restate=1"); 

        prestsum=con.prepareStatement("select * from Receipt where rerollno=? AND restate=1");//done

        predtsum1=con.prepareStatement("select * from Receipt where redate=? AND restate=1");

        predtsum2=con.prepareStatement("select * from Receipt where redate BETWEEN ? AND ? AND restate=1");

        precqsumpsl=con.prepareStatement("select * from Receipt where redetail like '%PSL' AND restate=1");

        precqsumrpl=con.prepareStatement("select * from Receipt where redetail like '%RPL' AND restate=1");

        precasum=con.prepareStatement("Select * from Receipt where redetail='Cash' AND restate=1");

        precnt=con.prepareStatement("select COUNT(reno) from Receipt");

        preserst=con.prepareStatement("select stfullname,stbtnm,stconm,stcofee,stadmfee,stfeepaid,stbalfee from student where stno=?");

        //Output Queries

        potadd=con.prepareStatement("insert into Output values(?,?,?,?,?)");

        potdel=con.prepareStatement("update Output set otstate=0");

        potdtrec=con.prepareStatement("select * from Output where otdate=? AND ottype='R'");

        potdtexp=con.prepareStatement("select * from Output where otdate=? AND ottype='E'");

        potsumdtrec=con.prepareStatement("select * from Output where otdate=? AND ottype='R'");

        potsumdtexp=con.prepareStatement("select * from Output where otdate=? AND ottype='E'");

        potcasum=con.prepareStatement("select * from Output where otdetail='ca' AND otdate=?");

        potcqsumpsl=con.prepareStatement("select * from Output where otdetail like '%PSL'");

        potcqdtsumpsl=con.prepareStatement("select * from Output where otdetail like '%PSL' AND otdate=?");

        potcqsumrpl=con.prepareStatement("select * from Output where otdetail like '%RPL'");

        potcqdtsumrpl=con.prepareStatement("select * from Output where otdetail like '%RPL' AND otdate=?");

        //sum of expences and receipts of a date
        potdtrecexp=con.prepareStatement("select * , ottype from Output where otdate=? group by ottype");


        //Student Queries

        pstadd=con.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        // pstmod=con.prepareStatement("update Student set stdate=? , stname=?, stclid=?, stclnm=?, sttrid=?,sttrtnm=?,stpaddr=?,stladdr=?,stctid=?,stctnm=?,stsmob=?,stpmob=?,stemail=?,stcoid=?,stconm=?, stbtid=?,stbtnm=?,stcofee=?,stadmfee=?,sttrntype=?,sttrndetail=?,sttrnto=?,stmode=? where stno=? AND ststate=1");
        pstmod=con.prepareStatement("update student set stdate=? , stname1=?,stname2=?,stname3=?,stfullname=?,stgender=?,stdob=?, stclnm=?,sttrnm=?,stacyr=?,stpaddr=?,stladdr=?,stctnm=?,stsmob=?,stpmob=?,stemail=?,stconm=?,stbtnm=?,bttime=?,stcofee=?,stadmfee=?,stfeepaid=?,stbalfee=?,sttrntype=?,sttrndetail=?,sttrnto=?,stmode=? WHERE stno=? AND ststate=1");
        
        pstdel=con.prepareStatement("update student set ststate=0 where stno=?");

        pstupdfee=con.prepareStatement("update student set stbalfee=? , stfeepaid = ? where stno=? and ststate=1");

        pstserno=con.prepareStatement("Select stno , stname1,stname,stname3 from student where stfullname=?");

        pstdis=con.prepareStatement("select * from student where ststate=1");

        pstdisnm=con.prepareStatement("select stno , stfullname , stconm from student where stfullname LIKE ? AND ststate=1");

        pstsernm=con.prepareStatement("select * from student where stname1=? AND stname2=? AND stname3=?");

        pstser=con.prepareStatement("select * from Student where  stno=?");

        pstcnt=con.prepareStatement("select COUNT(stno) from Student");

        pstfees=con.prepareStatement("select stfeepaid,stbalfee from student where stno=? and ststate=1");

        pstserbalfee=con.prepareStatement("select * from student where  stbalfee>0 and ststate=1 ORDER BY stbtnm");

        pstsertr=con.prepareStatement("Select * from Student where sttrnm=? and ststate=1");
        pstserbt=con.prepareStatement("Select * from Student where stbtnm=? and ststate=1");
        pstsercl=con.prepareStatement("Select * from Student where stclnm=? and ststate=1");
        pstserco=con.prepareStatement("Select * from Student where stconm=? and ststate=1");
        pstserct=con.prepareStatement("Select * from Student where stctnm=? and ststate=1");
    }
}