package co.cofarm.prj.farmer.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.cofarm.prj.common.Command;
import co.cofarm.prj.customer.service.CustomerService;
import co.cofarm.prj.customer.service.impl.CustomerServiceImpl;
import co.cofarm.prj.farmer.service.FarmerService;
import co.cofarm.prj.farmer.service.impl.FarmerServiceImpl;
import co.cofarm.prj.farmer.vo.FarmerVO;

public class FarmerJoinForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 농업인 회원가입 폼으로 보내기 폼으로 보낼 때, 이미 등록 정보 확인 후 보내기
		FarmerService dao = new FarmerServiceImpl();
		FarmerVO vo = new FarmerVO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		vo.setId(id);
		vo = dao.farmerSelect(vo);
		System.out.println(vo);
		String message = "이미 농업인 신청을 완료하였습니다. 승인을 기다려 주세요.";
		if(vo != null) {
			request.setAttribute("message", message);
			return "mypage/myPageMessage.tiles";
		} else {
			return "farmer/farmerJoinForm.tiles";
		}
	}
}
