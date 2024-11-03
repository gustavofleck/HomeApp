package com.example.homeapp.stubs

import com.example.homeapp.annotations.data.model.AnnotationFieldsResponse
import com.example.homeapp.annotations.data.model.AnnotationItemResponse
import com.example.homeapp.annotations.data.model.AnnotationResponseList
import com.example.homeapp.annotations.domain.model.AnnotationItem
import com.example.homeapp.common.model.BooleanFieldResponse
import com.example.homeapp.common.model.TextFieldResponse

internal val annotationList = listOf(
    AnnotationItem(
        id = "id1",
        text = "Text1",
        favorite = false,
        created = "createdAt",
        updated = "updatedAt"
    ),
    AnnotationItem(
        id = "id2",
        text = "Text2",
        favorite = true,
        created = "createdAt",
        updated = "updatedAt"
    )
)

internal val orderedAnnotationList = listOf(
    AnnotationItem(
        id = "id2",
        text = "Text2",
        favorite = true,
        created = "createdAt",
        updated = "updatedAt"
    ),
    AnnotationItem(
        id = "id1",
        text = "Text1",
        favorite = false,
        created = "createdAt",
        updated = "updatedAt"
    )
)

internal val annotationListResponse = AnnotationResponseList(
    documents = listOf(
        AnnotationItemResponse(
            name = "test/url/id1",
            fields = AnnotationFieldsResponse(
                text = TextFieldResponse("Text1"),
                favorite = BooleanFieldResponse(false)
            ),
            createTime = "createdAt",
            updateTime = "updatedAt"
        ),
        AnnotationItemResponse(
            name = "test/url/id2",
            fields = AnnotationFieldsResponse(
                text = TextFieldResponse("Text2"),
                favorite = BooleanFieldResponse(true)
            ),
            createTime = "createdAt",
            updateTime = "updatedAt"
        )
    )
)

internal val nullAnnotationListResponse = AnnotationResponseList(
    documents = null
)