package gregbakos.datacollector.jpa.test;

import gregbakos.datacollector.jpa.dao.Code;
import gregbakos.datacollector.jpa.dao.Device;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestQuery {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("DatacollectorJPA");
		EntityManager em = emf.createEntityManager();

		Code codeUj = em.find(Code.class, 46026L);
		Code codeKi = em.find(Code.class, 46027L);
		Code codeMegkapva = em.find(Code.class, 46157L);
		Code codeElkezdve = em.find(Code.class, 46158L);
		Code codeIsmetelni = em.find(Code.class, 46037L);
		Code codeOnFe = em.find(Code.class, 46162L);

		List<Long> activeCodes = Arrays.asList(codeUj.getId(), codeKi.getId(),
				codeMegkapva.getId(), codeElkezdve.getId(),
				codeIsmetelni.getId(), codeOnFe.getId());

		Device communicator = em.find(Device.class, 1000045L);

		Long min = 5L;
		Long max = 100L;
		int countLimit = 95;

		Long rfTaskId = null;

		// lekerjuk a taskok szamat
		Query queryRfTaskIdCount = em
				.createQuery("select count(t) from Task t where t.communicator = :communicator and t.rfTaskId between :taskIdMin and :taskIdMax");
		queryRfTaskIdCount.setParameter("communicator", communicator);
		queryRfTaskIdCount.setParameter("taskIdMin", min);
		queryRfTaskIdCount.setParameter("taskIdMax", max);

		Long count = (Long) queryRfTaskIdCount.getSingleResult();

		if (count < countLimit) {
			// ha meg van fel nem hasznalt rfTaskId
			rfTaskId = min + count;
		} else {
			// ha mar minden rfTaskId szerepel a task tablaban (4 .. 255)
			// lekerjuk a legkisebb szabad id-t
			Query queryRfTaskId = em
					.createQuery("select DISTINCT t2.rfTaskId from Task t2 where t2.communicator = :communicator "
							+ " and t2.rfTaskId between :taskIdMin and :taskIdMax"
							+ " and t2.taskStatus.id NOT IN :codes"
							+ " and t2.rfTaskId NOT IN ("
							+ " select t.rfTaskId from Task t where t.communicator = :communicator "
							+ " and t.rfTaskId between :taskIdMin and :taskIdMax"
							+ " and t.taskStatus.id IN :codes"
							+ " group by t.rfTaskId" + ")");
			queryRfTaskId.setParameter("taskIdMin", min);
			queryRfTaskId.setParameter("taskIdMax", max);
			queryRfTaskId.setParameter("codes", activeCodes);
			queryRfTaskId.setParameter("communicator", communicator);

			List<Long> ids = queryRfTaskId.getResultList();

			for (Long long1 : ids) {
				System.out.print(long1 + ",");
			}

			Random rnd = new Random();

			rfTaskId = ids.get(rnd.nextInt(ids.size()));

			if (rfTaskId == null) {
				// ha nincs szabad rfTaskId
				System.out.println("\nNincs szabad rfTaskId!");
			}
		}

		System.out.println("\nrfTaskId : " + rfTaskId);

		em.close();
		emf.close();
	}
}
