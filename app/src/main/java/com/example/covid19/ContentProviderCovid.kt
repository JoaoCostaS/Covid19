package com.example.covid19

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderCovid : ContentProvider() {
    private var bdCovidOpenHelper : BdCovidOpenHelper? = null

    override fun onCreate(): Boolean {
        bdCovidOpenHelper = BdCovidOpenHelper(context)

        return true
    }

    /**
     * Implement this to handle query requests from clients.
     *
     *
     * Apps targeting [android.os.Build.VERSION_CODES.O] or higher should override
     * [.query] and provide a stub
     * implementation of this method.
     *
     *
     * This method can be called from multiple threads, as described in
     * [Processes
 * and Threads]({@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads).
     *
     *
     * Example client call:
     *
     *
     * <pre>// Request a specific record.
     * Cursor managedCursor = managedQuery(
     * ContentUris.withAppendedId(Contacts.People.CONTENT_URI, 2),
     * projection,    // Which columns to return.
     * null,          // WHERE clause.
     * null,          // WHERE clause value substitution
     * People.NAME + " ASC");   // Sort order.</pre>
     * Example implementation:
     *
     *
     * <pre>// SQLiteQueryBuilder is a helper class that creates the
     * // proper SQL syntax for us.
     * SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
     *
     * // Set the table we're querying.
     * qBuilder.setTables(DATABASE_TABLE_NAME);
     *
     * // If the query ends in a specific record number, we're
     * // being asked for a specific record, so set the
     * // WHERE clause in our query.
     * if((URI_MATCHER.match(uri)) == SPECIFIC_MESSAGE){
     * qBuilder.appendWhere("_id=" + uri.getPathLeafId());
     * }
     *
     * // Make the query.
     * Cursor c = qBuilder.query(mDb,
     * projection,
     * selection,
     * selectionArgs,
     * groupBy,
     * having,
     * sortOrder);
     * c.setNotificationUri(getContext().getContentResolver(), uri);
     * return c;</pre>
     *
     * @param uri The URI to query. This will be the full URI sent by the client;
     * if the client is requesting a specific record, the URI will end in a record number
     * that the implementation should parse and add to a WHERE or HAVING clause, specifying
     * that _id value.
     * @param projection The list of columns to put into the cursor. If
     * `null` all columns are included.
     * @param selection A selection criteria to apply when filtering rows.
     * If `null` then all rows are included.
     * @param selectionArgs You may include ?s in selection, which will be replaced by
     * the values from selectionArgs, in order that they appear in the selection.
     * The values will be bound as Strings.
     * @param sortOrder How the rows in the cursor should be sorted.
     * If `null` then the provider is free to define the sort order.
     * @return a Cursor or `null`.
     */
    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        val bd = bdCovidOpenHelper!!.readableDatabase

        return when (getUriMacther().match(uri)){
            URI_CIDADES -> TabelaCidades(bd).query(
                    projection as Array<String>,
                    selection,
                    selectionArgs as Array<String>?,
                    null,
                    null,
                    sortOrder
            )
            URI_CIDADE_ESPECIFICA -> TabelaCidades(bd).query(
                    projection as Array<String>,
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
                    null,
                    null,
                    null
            )

            URI_CASOS -> TabelaCasos(bd).query(
                    projection as Array<String>,
                    selection,
                    selectionArgs as Array<String>?,
                    null,
                    null,
                    sortOrder
            )
            URI_CASOS_ESPECIFICOS -> TabelaCasos(bd).query(
                    projection as Array<String>,
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
                    null,
                    null,
                    null
            )/*
            URI_VACINAS -> TabelaVacinacao(bd).query(
                    projection as Array<String>,
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
                    null,
                    null,
                    sortOrder
            )
            URI_VACINAS_ESPECIFICAS -> TabelaVacinacao(bd).query(
                    projection as Array<String>,
                    selection,
                    selectionArgs as Array<String>?,
                    null,
                    null,
                    null
            )*/
            URI_FOCO_CONTAGIO -> TabelaFocoContagio(bd).query(
                    projection as Array<String>,
                    selection,
                    selectionArgs as Array<String>?,
                    null,
                    null,
                    sortOrder
            )
            URI_FOCO_CONTAGIO_ESPECIFICOS -> TabelaFocoContagio(bd).query(
                    projection as Array<String>,
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
                    null,
                    null,
                    null
            )
            else -> null
        }
    }

    /**
     * Implement this to handle requests for the MIME type of the data at the
     * given URI.  The returned MIME type should start with
     * `vnd.android.cursor.item` for a single record,
     * or `vnd.android.cursor.dir/` for multiple items.
     * This method can be called from multiple threads, as described in
     * [Processes
 * and Threads]({@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads).
     *
     *
     * Note that there are no permissions needed for an application to
     * access this information; if your content provider requires read and/or
     * write permissions, or is not exported, all applications can still call
     * this method regardless of their access permissions.  This allows them
     * to retrieve the MIME type for a URI when dispatching intents.
     *
     * @param uri the URI to query.
     * @return a MIME type string, or `null` if there is no type.
     */
    override fun getType(uri: Uri): String? {
        return when (getUriMacther().match(uri)){
            URI_CIDADES -> "$MULTIPLOS_ITEMS/$Cidades"
            URI_CIDADE_ESPECIFICA -> "$UNICO_ITEM/$Cidades"
            URI_CASOS -> "$MULTIPLOS_ITEMS/$Casos"
            URI_CASOS_ESPECIFICOS -> "$UNICO_ITEM/$Casos"
           // URI_VACINAS -> "$MULTIPLOS_ITEMS/$Vacinas"
            //URI_VACINAS_ESPECIFICAS -> "$UNICO_ITEM/$Vacinas"
            URI_FOCO_CONTAGIO -> "$MULTIPLOS_ITEMS/$FocoContagio"
            URI_FOCO_CONTAGIO_ESPECIFICOS -> "$UNICO_ITEM/$FocoContagio"
            else -> null
        }
    }

    /**
     * Implement this to handle requests to insert a new row. As a courtesy,
     * call
     * [ notifyChange()][ContentResolver.notifyChange] after inserting. This method can be called from multiple
     * threads, as described in [Processes
 * and Threads](
      {@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads).
     *
     * @param uri The content:// URI of the insertion request.
     * @param values A set of column_name/value pairs to add to the database.
     * @return The URI for the newly inserted item.
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val bd = bdCovidOpenHelper!!.writableDatabase

        val id = when (getUriMacther().match(uri)){

            URI_CIDADES -> TabelaCidades(bd).insert(values!!)

            URI_CASOS -> TabelaCasos(bd).insert(values!!)

            //URI_VACINAS -> TabelaVacinacao(bd).insert(values!!)

            URI_FOCO_CONTAGIO -> TabelaFocoContagio(bd).insert(values!!)

            else -> -1
        }
        if(id == -1L) return null

        return Uri.withAppendedPath(uri, id.toString())
    }

    /**
     * Implement this to handle requests to delete one or more rows. The
     * implementation should apply the selection clause when performing
     * deletion, allowing the operation to affect multiple rows in a directory.
     * As a courtesy, call
     * [ notifyChange()][ContentResolver.notifyChange] after deleting. This method can be called from multiple
     * threads, as described in [Processes
 * and Threads](
      {@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads).
     *
     *
     * The implementation is responsible for parsing out a row ID at the end of
     * the URI, if a specific row is being deleted. That is, the client would
     * pass in `content://contacts/people/22` and the implementation
     * is responsible for parsing the record number (22) when creating a SQL
     * statement.
     *
     * @param uri The full URI to query, including a row ID (if a specific
     * record is requested).
     * @param selection An optional restriction to apply to rows when deleting.
     * @return The number of rows affected.
     * @throws SQLException
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val bd = bdCovidOpenHelper!!.writableDatabase

        return when (getUriMacther().match(uri)){
            /* URI_CIDADE_ESPECIFICA -> TabelaCidades(bd).query(
                     projection as Array<String>,
                     selection,
                     selectionArgs as Array<String>?,
                     null,
                     null,
                     null
             )*/

            URI_CASOS_ESPECIFICOS -> TabelaCasos(bd).delete(
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
            )
/*
            URI_VACINAS_ESPECIFICAS -> TabelaVacinacao(bd).delete(
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
            )*/
            URI_FOCO_CONTAGIO_ESPECIFICOS -> TabelaFocoContagio(bd).delete(
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
            )
            else -> 0
        }
    }

    /**
     * Implement this to handle requests to update one or more rows. The
     * implementation should update all rows matching the selection to set the
     * columns according to the provided values map. As a courtesy, call
     * [ notifyChange()][ContentResolver.notifyChange] after updating. This method can be called from multiple
     * threads, as described in [Processes
 * and Threads](
      {@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads).
     *
     * @param uri The URI to query. This can potentially have a record ID if
     * this is an update request for a specific record.
     * @param values A set of column_name/value pairs to update in the database.
     * @param selection An optional filter to match rows to update.
     * @return the number of rows affected.
     */
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val bd = bdCovidOpenHelper!!.writableDatabase

        return when (getUriMacther().match(uri)){
           /* URI_CIDADE_ESPECIFICA -> TabelaCidades(bd).query(
                    projection as Array<String>,
                    selection,
                    selectionArgs as Array<String>?,
                    null,
                    null,
                    null
            )*/

            URI_CASOS_ESPECIFICOS -> TabelaCasos(bd).update(
                    values!!,
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
            )
/*
            URI_VACINAS_ESPECIFICAS -> TabelaVacinacao(bd).update(
                    values!!,
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
            )*/
            URI_FOCO_CONTAGIO_ESPECIFICOS -> TabelaFocoContagio(bd).update(
                    values!!,
                    "${BaseColumns._ID}=?",
                    arrayOf(uri.lastPathSegment!!),
            )
            else -> 0
        }
    }
    companion object{
        private const val AUTHORITY = "com.example.covid19"

        private const val Cidades = "cidades"
        private const val Casos = "casos"
        //private const val Vacinas = "vacinas"
        private const val FocoContagio = "focoContagio"

        private const val URI_CIDADES = 100
        private const val URI_CIDADE_ESPECIFICA = 101
        private const val URI_CASOS = 200
        private const val URI_CASOS_ESPECIFICOS = 201
        //private const val URI_VACINAS = 300
        //private const val URI_VACINAS_ESPECIFICAS = 301
        private const val URI_FOCO_CONTAGIO = 400
        private const val URI_FOCO_CONTAGIO_ESPECIFICOS = 401

        private const val MULTIPLOS_ITEMS = "vnd.android.cursor.dir"
        private const val UNICO_ITEM = "vnd.android.cursor.item"

        private val ENDERECO_BASE = Uri.parse("content://$AUTHORITY/")
        public val ENDERECO_CIDADES = Uri.withAppendedPath(ENDERECO_BASE, Cidades)
        public val ENDERECO_CASOS = Uri.withAppendedPath(ENDERECO_BASE, Casos)
        public val ENDERECO_FOCO_CONTAGIO = Uri.withAppendedPath(ENDERECO_BASE, FocoContagio)

        private fun getUriMacther() : UriMatcher{
            val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, Cidades, URI_CIDADES)
            uriMatcher.addURI(AUTHORITY, "$Cidades/#", URI_CIDADE_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, Casos, URI_CASOS)
            uriMatcher.addURI(AUTHORITY, "$Casos/#", URI_CASOS_ESPECIFICOS)
            //uriMatcher.addURI(AUTHORITY, Vacinas, URI_VACINAS)
            //uriMatcher.addURI(AUTHORITY, "$Vacinas/#", URI_VACINAS_ESPECIFICAS)
            uriMatcher.addURI(AUTHORITY, FocoContagio, URI_FOCO_CONTAGIO)
            uriMatcher.addURI(AUTHORITY, "$FocoContagio/#", URI_FOCO_CONTAGIO_ESPECIFICOS)

            return uriMatcher
        }
    }
}