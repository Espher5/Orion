import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.beans.inserzioni.IntervalloDisponibilitÓBean;
import model.dataAccessObjects.inserzioni.IntervalloDisponibilitÓDao;
import model.dataAccessObjects.inserzioni.IntervalloDisponibilitÓDaoImpl;

public class Test {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long idInserzione = 1;
		Date checkInDate = format.parse("2021-01-04");
		Date checkOutDate = format.parse("2021-01-08");
		
		IntervalloDisponibilitÓDao idd = new IntervalloDisponibilitÓDaoImpl();
		IntervalloDisponibilitÓBean idb = idd.doRetrieveByDate(1, checkInDate, checkOutDate);
		
		idd.doDelete(idInserzione, idb.getDataInizio(), idb.getDataFine());
		
		Calendar c = Calendar.getInstance();
		c.setTime(checkInDate);
		c.add(Calendar.DATE, 0);
		
		IntervalloDisponibilitÓBean id1 = new IntervalloDisponibilitÓBean();
		id1.setIdInserzione(idInserzione);
		id1.setDataInizio(idb.getDataInizio());
		id1.setDataFine(c.getTime());
		idd.doSave(id1);
		
		c = Calendar.getInstance();
		c.setTime(checkOutDate);
		c.add(Calendar.DATE, 2);
		IntervalloDisponibilitÓBean id2 = new IntervalloDisponibilitÓBean();
		id2.setIdInserzione(idInserzione);
		id2.setDataInizio(c.getTime());
		id2.setDataFine(idb.getDataFine());
		idd.doSave(id2);
		
		List<IntervalloDisponibilitÓBean> intervalli = idd.doRetrieveByIdInserzione(1);
		for(IntervalloDisponibilitÓBean i : intervalli) {
			System.out.println(i.getDataInizio() + "  ///  " + i.getDataFine());	
		}

	}
}
