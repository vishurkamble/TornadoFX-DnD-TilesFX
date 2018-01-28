package com.example.demo.model

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javax.json.JsonObject
import tornadofx.*

class HomepageGridBuilder: JsonModel {
    private val gridProperty = SimpleIntegerProperty()
    var grid by gridProperty

    private val rowsProperty = SimpleIntegerProperty()
    var rows by rowsProperty

    private val columnsProperty = SimpleIntegerProperty()
    var columns by columnsProperty

    var tiles = listOf<HomepageTileBuilder>()

    override fun toString(): String {
        return "HomepageGrid(grid=$grid, rows=$rows, columns=$columns, tiles=$tiles)"
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            grid = int("grid") ?: 0
            rows = int("rows") ?: 1
            columns = int("columns") ?: 1
            tiles = getJsonArray("tiles").toModel()
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("grid", grid)
            add("rows", rows)
            add("columns", columns)
            add("tiles", tiles.toJSON())
        }
    }
}

class HomepageTileBuilder: JsonModel {
    private val titleProperty = SimpleStringProperty()
    var title by titleProperty

    private val colIndexProperty = SimpleIntegerProperty()
    var colIndex by colIndexProperty

    private val rowIndexProperty = SimpleIntegerProperty()
    var rowIndex by rowIndexProperty

    private val colSpanProperty = SimpleIntegerProperty()
    var colSpan by colSpanProperty

    private val rowSpanProperty = SimpleIntegerProperty()
    var rowSpan by rowSpanProperty

    private val widthProperty = SimpleDoubleProperty()
    var width by widthProperty

    private val heightProperty = SimpleDoubleProperty()
    var height by heightProperty

    override fun toString(): String {
        return "Tile(title=$title, colIndex=$colIndex, rowIndex=$rowIndex," +
        "colSpan=$colSpan, rowSpan=$rowSpan, width=$width, height=$height)"
    }

    override fun updateModel(json: JsonObject) {
        with (json) {
            title = string("title")
            colIndex = int("colIndex") ?: 1
            rowIndex = int("rowIndex") ?: 1
            colSpan = int("colSpan") ?: 1
            rowSpan = int("rowSpan") ?: 1
            width = double("width") ?: 100.0
            height = double("height") ?: 100.0
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("title", title)
            add("colIndex", colIndex)
            add("rowIndex", rowIndex)
            add("colSpan", colSpan)
            add("rowSpan", rowSpan)
            add("width", width)
            add("height", height)
        }
    }
}