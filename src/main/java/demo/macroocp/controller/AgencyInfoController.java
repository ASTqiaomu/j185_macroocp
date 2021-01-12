package demo.macroocp.controller;

import demo.macroocp.bean.AgencyInfo;
import demo.macroocp.services.AgencyInfoServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 经销商信息 Controller
 */
@CrossOrigin
@RestController
public class AgencyInfoController
{
	@Resource
	private AgencyInfoServices agencyInfoServices;

	@RequestMapping("/getdeliveradbyagencyname")
	public Map<String, Object> getDeliverAdByAgencyName(AgencyInfo agencyInfo)
	{
		return this.agencyInfoServices.getDeliverAdByAgencyName(agencyInfo);
	}

	@RequestMapping("/getallbydeliverad")
	public Map<String, Object> getAllByDeliverAd(AgencyInfo agencyInfo)
	{
		return this.agencyInfoServices.getAllByDeliverAd(agencyInfo);
	}

	@RequestMapping("/getagencyname")
	public Map<String, Object> getAgencyName()
	{
		return this.agencyInfoServices.getAgencyName();
	}

	@RequestMapping("/getagencyid")
	public Map<String, Object> getAgencyID(AgencyInfo agencyInfo)
	{
		return this.agencyInfoServices.getAgencyID(agencyInfo);
	}
}
