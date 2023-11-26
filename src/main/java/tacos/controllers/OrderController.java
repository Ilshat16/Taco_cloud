package tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import tacos.models.TacoOrder;
import tacos.repositoryes.order.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	
	private OrderRepository orderRepository;
	

	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors,
			SessionStatus sessionStatus) {
		if (errors.hasErrors())
			return "orderForm";

		orderRepository.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
	@GetMapping("/{name}")
	public String showTaco(@PathVariable("name") String name, TacoOrder order, Model model) {
		model.addAttribute("taco", order.getTacos().stream()
				.filter(t -> t.getName().equals(name)).toList().get(0));
		return "showTaco";
	}
}
