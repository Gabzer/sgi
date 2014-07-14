package net.felansu.sgi.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.felansu.sgi.dao.ParticipanteDao;
import net.felansu.sgi.model.Participante;
import net.felansu.sgi.model.Vagas;
import net.felansu.sgi.util.ValidadoresUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class ParticipanteBean implements Serializable {
	private static final long serialVersionUID = 186534654000743728L;

	private Participante participante;
	private UploadedFile fileUploaded;
	private StreamedContent fileDownload;
	private ParticipanteDao dao;
	private boolean formAdmin = false;
	private boolean cadastroFinalizado = false;
	private String formAdminSenha;


	public String getFormAdminSenha() {
		return formAdminSenha;
	}

	public void setFormAdminSenha(String formAdminSenha) {
		this.formAdminSenha = formAdminSenha;
	}

	public ParticipanteBean() throws FileNotFoundException {
		this.participante = new Participante();
		this.dao = new ParticipanteDao();
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public StreamedContent getFileDownload() throws IOException {
		File file = new File(this.participante.getAnexo());
		InputStream stream = new FileInputStream(file);
		fileDownload = new DefaultStreamedContent(stream, Files.probeContentType(Paths.get(file.getPath())), file.getName());
		this.participante = new Participante();
		return fileDownload;
	}

	public UploadedFile getFileUploaded() {
		return fileUploaded;
	}

	public void setFileUploaded(UploadedFile fileUploaded) {
		this.fileUploaded = fileUploaded;
	}

	public Vagas[] getVagasValues() {
		return Vagas.values();
	}

	public boolean isFormAdmin() {
		return formAdmin;
	}

	public void setFormAdmin(boolean formAdmin) {
		this.formAdmin = formAdmin;
	}

	public boolean isCadastroFinalizado() {
		return cadastroFinalizado;
	}

	public void setCadastroFinalizado(boolean cadastroFinalizado) {
		this.cadastroFinalizado = cadastroFinalizado;
	}

	public void cadastrarParticipante() throws Exception {
		if (ValidadoresUtil.validaCPF(this.participante.getCpf())) {
			if (!dao.verificarDadosExistentes(this.participante.getCpf(), this.participante.getRg())) {
				if (upload()) {
					dao.salvar(this.participante);
					setCadastroFinalizado(true);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso !", this.participante.getNome() + " foi cadastrado no sistema."));
					this.participante = new Participante();
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF ou RG já cadastrado", "Verifique se você já foi cadastrado no sistema."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF Inválido", "Verifique se digitou o seu CPF corretamente."));
		}
	}

	public boolean upload() throws Exception {
		if (fileUploaded.getSize() != 0) {
			String nome = fileUploaded.getFileName();
			String rota = "c://temp//" + nome;
			File file = new File(rota);
			InputStream is = null;
			try (OutputStream os = new FileOutputStream(file)) {
				is = fileUploaded.getInputstream();
				file.getParentFile().mkdirs();
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = is.read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				System.out.println("Arquivo anexado com sucesso!");
			} catch (IOException e) {
				new Throwable("Erro na leitura do arquivo. Descição: \n" + e.getMessage());
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					throw new Exception("Erro ao fechar arquivos. Descrição: \n " + e.getMessage());
				}
			}
			this.participante.setAnexo(rota);
			return true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falta arquivo anexo", "É requerido anexar o histórico escolar"));
			return false;
		}
	}

	public List<Participante> getListarParticipantes() {
		return dao.listar();
	}

	public void validarSenhaAdmin() {
		if (this.formAdminSenha.equals("cecane2014!@")) {
			setFormAdmin(true);
		}
	}


	public void gerarRelatorio() throws Exception {
		Map<String, Object> parametros = new HashMap<>();
		File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/participantes.jasper"));

		JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parametros, new JRBeanCollectionDataSource(dao.listar()));
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
		ServletOutputStream sos = response.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jp, sos);

		sos.flush();
		sos.close();
		FacesContext.getCurrentInstance().responseComplete();
		}
	}