package com.metinvandar.yourservice

import com.metinvandar.yourservice.data.model.Service
import com.metinvandar.yourservice.domain.mappers.ServiceItemMapper
import org.junit.Test

class ServiceItemMapperTest {

    private val mapper = ServiceItemMapper()

    @Test
    fun map_service_items_correctly() {
        val serviceListToMap = mockServiceList()
        val mappedList = mapper.mapToUIModelList(serviceListToMap)

        assert(serviceListToMap.size == mappedList.size)
        for (i in mappedList.indices) {
            assert(mappedList[i].id == serviceListToMap[i].id)
        }
    }

    private fun mockServiceList(): List<Service> {

        return ArrayList<Service>().apply {
            add(
                Service(
                    id = 1,
                    serviceId = 1,
                    name = "",
                    type = "",
                    imageUrl = ""
                )
            )

            add(
                Service(
                    id = 2,
                    serviceId = 2,
                    name = "",
                    type = "",
                    imageUrl = ""
                )
            )
            add(
                Service(
                    id = 3,
                    serviceId = 3,
                    name = "",
                    type = "",
                    imageUrl = ""
                )
            )

            add(
                Service(
                    id = 4,
                    serviceId = 4,
                    name = "",
                    type = "",
                    imageUrl = ""
                )
            )
        }
    }
}