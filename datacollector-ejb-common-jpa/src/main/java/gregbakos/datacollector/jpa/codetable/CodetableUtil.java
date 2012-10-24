package gregbakos.datacollector.jpa.codetable;

import gregbakos.datacollector.IptException;
import gregbakos.datacollector.jpa.dao.Code;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CodetableUtil {
	public static Code getCodeByCodes(EntityManager em, String codeGroupCode,
			String code) {
		Query codeQuery = em.createQuery("SELECT OBJECT(k) FROM Code k "
				+ " WHERE k.codeGroup.code = :codeGroupCode "
				+ " AND k.code = :code ");
		codeQuery.setParameter("codeGroupCode", codeGroupCode);
		codeQuery.setParameter("code", code);
		try {
			Code k;
			k = (Code) codeQuery.getSingleResult();
			return k;
		} catch (Exception ex) {
			System.out.println("fetched code:" + codeGroupCode + "/" + code);
			System.out.println(ex);
			throw new IptException("missingCode");
		}
	}
}
