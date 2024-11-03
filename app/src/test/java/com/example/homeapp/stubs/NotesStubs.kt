package com.example.homeapp.stubs

import com.example.homeapp.notes.data.model.AnnotationFieldsResponse
import com.example.homeapp.notes.data.model.NoteResponse
import com.example.homeapp.notes.data.model.NoteResponseList
import com.example.homeapp.notes.domain.model.Note
import com.example.homeapp.common.model.BooleanFieldResponse
import com.example.homeapp.common.model.TextFieldResponse

internal val noteList = listOf(
    Note(
        id = "id1",
        text = "Text1",
        favorite = false,
        created = "createdAt",
        updated = "updatedAt"
    ),
    Note(
        id = "id2",
        text = "Text2",
        favorite = true,
        created = "createdAt",
        updated = "updatedAt"
    )
)

internal val orderedNoteList = listOf(
    Note(
        id = "id2",
        text = "Text2",
        favorite = true,
        created = "createdAt",
        updated = "updatedAt"
    ),
    Note(
        id = "id1",
        text = "Text1",
        favorite = false,
        created = "createdAt",
        updated = "updatedAt"
    )
)

internal val noteListResponse = NoteResponseList(
    documents = listOf(
        NoteResponse(
            name = "test/url/id1",
            fields = AnnotationFieldsResponse(
                text = TextFieldResponse("Text1"),
                favorite = BooleanFieldResponse(false)
            ),
            createTime = "createdAt",
            updateTime = "updatedAt"
        ),
        NoteResponse(
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

internal val nullAnnotationListResponse = NoteResponseList(
    documents = null
)