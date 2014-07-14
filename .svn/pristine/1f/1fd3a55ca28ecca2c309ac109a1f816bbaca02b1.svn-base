package net.felansu.sgi.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.felansu.sgi.model.Participante;
import net.felansu.sgi.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ParticipanteDao implements Serializable {
	private static final long serialVersionUID = 651693130363326039L;

	private Session sessao;
	private Transaction transacao;

	public void salvar(Participante participante) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(participante);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir um participante. Error: " + e.getMessage());
			new Throwable();
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção. Mnesagem: " + e.getMessage());
			}
		}
	}

	public boolean verificarDadosExistentes(String cpf, String rg) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();

			org.hibernate.Criteria criteria = this.sessao.createCriteria(Participante.class);
			criteria.add(Restrictions.eq("cpf", cpf));
			criteria.setProjection(Projections.rowCount());
			long cpfCount = (Long) criteria.uniqueResult();
			sessao.getTransaction().commit();

			// Limpamos os criterios
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			criteria = this.sessao.createCriteria(Participante.class);
			criteria.setProjection(null);
			criteria.setResultTransformer(criteria.ROOT_ENTITY);
			criteria.add(Restrictions.eq("rg", rg));
			criteria.setProjection(Projections.rowCount());
			long rgCount = (Long) criteria.uniqueResult();
			sessao.getTransaction().commit();

			if (cpfCount != 0 || rgCount != 0) {
				return true;
			} else {
				return false;
			}

		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir um participante. Error: " + e.getMessage());
		}
		return true;

	}

	public List<Participante> listar() {
		List<Participante> participantes = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Participante.class);
			participantes = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Não foi possível inserir um participante. Error: " + e.getMessage());
			}
		}
		return participantes;
	}


}
