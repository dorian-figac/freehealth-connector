/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.web.controllers

import be.fgov.ehealth.hubservices.core.v2.ConsentType
import ma.glasnost.orika.MapperFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.consent.ConsentMessageDto
import org.taktik.freehealth.middleware.dto.consent.ConsentTypeDto
import org.taktik.freehealth.middleware.service.ConsentService
import java.util.*

@RestController
@RequestMapping("/consent")
class ConsentController(val consentService: ConsentService, val mapper : MapperFacade) {

	@PostMapping("/{patientSsin}")
	fun registerPatientConsent(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String,
	                           @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String,
	                           @PathVariable patientSsin: String, @RequestParam patientFirstName: String, @RequestParam patientLastName: String,
	                           @RequestParam(required = false) eidCardNumber : String? = null, @RequestParam(required = false) isiCardNumber : String? = null) = consentService.registerPatientConsent(
			keystoreId = keystoreId,
			tokenId = tokenId,
			passPhrase = passPhrase,
			hcpNihii = hcpNihii,
			hcpSsin = hcpSsin,
			hcpFirstName = hcpFirstName,
			hcpLastName = hcpLastName,
			patientSsin = patientSsin,
			patientFirstName = patientFirstName,
			patientLastName = patientLastName,
			eidCardNumber = eidCardNumber,
			isiCardNumber = isiCardNumber
	).let{ mapper.map(it, ConsentMessageDto::class.java) }

	@GetMapping("/{patientSsin}")
	fun getPatientConsent(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String,
	                           @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String,
	                           @PathVariable patientSsin: String, @RequestParam patientFirstName: String, @RequestParam patientLastName: String) = consentService.getPatientConsent(
			keystoreId = keystoreId,
			tokenId = tokenId,
			passPhrase = passPhrase,
			hcpNihii = hcpNihii,
			hcpSsin = hcpSsin,
			hcpFirstName = hcpFirstName,
			hcpLastName = hcpLastName,
			patientSsin = patientSsin,
			patientFirstName = patientFirstName,
			patientLastName = patientLastName
	).let{ mapper.map(it, ConsentMessageDto::class.java) }

	@PostMapping("/revoke/{patientSsin}")
	fun revokePatientConsent(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String,
	                         @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String,
	                         @RequestBody existingConsent : ConsentTypeDto,
	                         @RequestParam(required = false) eidCardNumber : String? = null, @RequestParam(required = false) isiCardNumber : String? = null) = consentService.revokePatientConsent(
			keystoreId = keystoreId,
			tokenId = tokenId,
			passPhrase = passPhrase,
			hcpNihii = hcpNihii,
			hcpSsin = hcpSsin,
			hcpFirstName = hcpFirstName,
			hcpLastName = hcpLastName,
			existingConsent = mapper.map(existingConsent, ConsentType::class.java),
			eidCardNumber = eidCardNumber,
			isiCardNumber = isiCardNumber
	).let{ mapper.map(it, ConsentMessageDto::class.java) }

}