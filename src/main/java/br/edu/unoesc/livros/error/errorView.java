package br.edu.unoesc.livros.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class errorView implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		System.out.println("______________________");
		map.forEach((chave, valor) -> System.out.println(chave + ":" + valor));
		System.out.println("______________________");

		ModelAndView model = new ModelAndView("/error");
		model.addObject("status", status.value());

		switch (status.value()) {
		case 404:
			model.addObject("error", "Pagina não encontrada");
			model.addObject("message", "A URL para a pagina" + map.get("path") + " não existe");
			break;
		case 500:
			model.addObject("error", "Erro interno do servidor");
			model.addObject("message", "Erro interno tente mais tarde");
			break;
		default:
			model.addObject("error", map.get("error"));
			model.addObject("message", "Erro interno tente mais tarde");

		}

		return model;
	}

}
