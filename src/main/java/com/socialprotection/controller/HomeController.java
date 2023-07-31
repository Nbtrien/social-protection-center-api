package com.socialprotection.controller;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.socialprotection.entity.Budget;
import com.socialprotection.entity.ChildrenStatus;
import com.socialprotection.entity.Job;
import com.socialprotection.entity.Role;
import com.socialprotection.entity.Shift;
import com.socialprotection.entity.TypeOfOrphan;
import com.socialprotection.entity.User;
import com.socialprotection.repository.BudgetRepository;
import com.socialprotection.repository.JobRepository;
import com.socialprotection.repository.ShiftRepository;
import com.socialprotection.service.ChildrenService;
import com.socialprotection.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@Autowired
	ChildrenService childrenService;

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ShiftRepository shiftRepository;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public void homePage() {
		Role role = userService.saveRole(new Role("SUPER_ADMIN","Quyền quản trị cao nhất, có quyền quản lý tài khoản admin"));
		Role role2 = userService.saveRole(new Role("ADMIN", "Quyền quản trị hệ thống, thực hiện thêm, sửa, xóa các thông tin của trung tâm"));
		Role role3 = userService.saveRole(new Role("WEB_ADMIN", "Quyền quản trị website, thêm sửa xóa các bài viết và nội dung hiển thị trên website"));

		userService.saveUser(new User("SUPERADMIM01", "superadmin@01", role));

		userService.saveUser(new User("ADMIN01", "admin@01", role2));
		userService.saveUser(new User("ADMIN03", "admin@03", role3));

		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi cha mẹ",
				"Đây là loại trẻ mồ côi phổ biến nhất, khi cha mẹ của chúng đã qua đời hoặc không thể nuôi dưỡng chúng vì nhiều lý do khác nhau, bao gồm chiến tranh, bạo lực gia đình hay bệnh tật."));
		childrenService.saveTypeOrphan(
				new TypeOfOrphan("Trẻ mồ côi cha", "Trẻ mồ côi loại này chỉ mất cha do tử vong hoặc bỏ rơi."));
		childrenService.saveTypeOrphan(
				new TypeOfOrphan("Trẻ mồ côi mẹ", "Trẻ mồ côi loại này chỉ mất mẹ do tử vong hoặc bỏ rơi."));
		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi vì bố mẹ ly dị hoặc bị bỏ rơi",
				"Trẻ mồ côi loại này có thể do cha mẹ ly dị, bị bỏ rơi hoặc không có ai chịu trách nhiệm nuôi dưỡng"));
		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi do bạo lực gia đình",
				"Những trẻ mồ côi này thường là nạn nhân của bạo lực gia đình, bị cha mẹ hoặc người giám hộ bạo hành hoặc bỏ rơi"));
		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi do cha mẹ bị bắt tù",
				"Trẻ mồ côi loại này là con của phạm nhân bị bắt tù, không có ai chịu trách nhiệm nuôi dưỡng."));

		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi cha mẹ",
				"Đây là loại trẻ mồ côi phổ biến nhất, khi cha mẹ của chúng đã qua đời hoặc không thể nuôi dưỡng chúng vì nhiều lý do khác nhau, bao gồm chiến tranh, bạo lực gia đình hay bệnh tật."));
		childrenService.saveTypeOrphan(
				new TypeOfOrphan("Trẻ mồ côi cha", "Trẻ mồ côi loại này chỉ mất cha do tử vong hoặc bỏ rơi."));
		childrenService.saveTypeOrphan(
				new TypeOfOrphan("Trẻ mồ côi mẹ", "Trẻ mồ côi loại này chỉ mất mẹ do tử vong hoặc bỏ rơi."));
		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi vì bố mẹ ly dị hoặc bị bỏ rơi",
				"Trẻ mồ côi loại này có thể do cha mẹ ly dị, bị bỏ rơi hoặc không có ai chịu trách nhiệm nuôi dưỡng"));
		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi do bạo lực gia đình",
				"Những trẻ mồ côi này thường là nạn nhân của bạo lực gia đình, bị cha mẹ hoặc người giám hộ bạo hành hoặc bỏ rơi"));
		childrenService.saveTypeOrphan(new TypeOfOrphan("Trẻ mồ côi do cha mẹ bị bắt tù",
				"Trẻ mồ côi loại này là con của phạm nhân bị bắt tù, không có ai chịu trách nhiệm nuôi dưỡng."));

		childrenService
				.saveChildrenStatus(new ChildrenStatus("Đang được bảo trợ", "Trẻ em vẫn đang được bảo trợ"));
		childrenService.saveChildrenStatus(new ChildrenStatus("Đã được nhận nuôi", "Trẻ em đã được nhận nuôi"));
		childrenService.saveChildrenStatus(new ChildrenStatus("Đã quá tuổi", "Trẻ em đã quá tuổi"));

		jobRepository.save(new Job("Chăm sóc trẻ",
				"- Chăm sóc và nuôi dưỡng các trẻ em mồ côi. Chia sẻ cuộc sống của mình với các con, chăm sóc, nuôi dạy, che chở và mang lại cho các con cơ hội phát triển, những mối quan hệ mới, lâu dài và tình thương yêu trong gia đình SOS của mình."));
		jobRepository.save(new Job("Nấu ăn", "Nấu ăn"));

		shiftRepository.save(new Shift("Ca 1", new Time(6, 0, 0), new Time(14, 0, 0)));
		shiftRepository.save(new Shift("Ca 2", new Time(14, 0, 0), new Time(22, 0, 0)));
		shiftRepository.save(new Shift("Ca 3", new Time(22, 0, 0), new Time(6, 0, 0)));

		budgetRepository.save(new Budget("tien quy", "xay dung", 10000, null, null));
		budgetRepository.save(new Budget("tien an", "an uong", 20000, null, null));
	}
}